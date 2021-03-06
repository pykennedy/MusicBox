package pyk.musicbox.view.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pyk.musicbox.R;
import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.service.PlaybackManager;
import pyk.musicbox.service.PlaybackService;
import pyk.musicbox.service.PlaylistManager;
import pyk.musicbox.view.fragment.BaseMenuFragment;
import pyk.musicbox.view.fragment.SearchFragment;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView, Listener.FragmentListener, Listener.PlaybackControlListener, View.OnClickListener {
  
  private MainActivityPresenter mainActivityPresenter;
  private BaseMenuFragment      baseMenuFragment;
  private BaseFragment          menuFragment;
  private Menu                  menu;
  private ViewGroup             playback;
  private TextView              title;
  private TextView              details;
  private ImageButton           back;
  private ImageButton           playPause;
  private ImageButton           forward;
  private SeekBar               seekBar;
  private String                    currentID;
  private long playlistID = -1;
  private PlaylistManager           playlistManager;
  
  private MediaMetadataCompat currentMetadata;
  private PlaybackStateCompat currentState;
  
  private MediaBrowserCompat mediaBrowser;
  
  private final MediaBrowserCompat.ConnectionCallback connectionCallback =
      new MediaBrowserCompat.ConnectionCallback() {
        @Override
        public void onConnected() {
          mediaBrowser.subscribe(mediaBrowser.getRoot(), subscriptionCallback);
          try {
            MediaControllerCompat mediaController =
                new MediaControllerCompat(
                    MainActivity.this, mediaBrowser.getSessionToken());
            updatePlaybackState(mediaController.getPlaybackState());
            updateMetadata(mediaController.getMetadata());
            mediaController.registerCallback(mediaControllerCallback);
            MediaControllerCompat.setMediaController(
                MainActivity.this, mediaController);
          } catch (RemoteException e) {
            throw new RuntimeException(e);
          }
        }
      };
  
  // Receive callbacks from the MediaController. Here we update our state such as which queue
  // is being shown, the current title and description and the PlaybackState.
  private final MediaControllerCompat.Callback mediaControllerCallback =
      new MediaControllerCompat.Callback() {
        @Override
        public void onMetadataChanged(MediaMetadataCompat metadata) {
          updateMetadata(metadata);
        }
        
        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
          updatePlaybackState(state);
        }
        
        @Override
        public void onSessionDestroyed() {
          updatePlaybackState(null);
        }
      };
  
  private final MediaBrowserCompat.SubscriptionCallback subscriptionCallback =
      new MediaBrowserCompat.SubscriptionCallback() {
        @Override
        public void onChildrenLoaded(
            String parentId, List<MediaBrowserCompat.MediaItem> children) {
        }
      };
  
  private void onMediaItemSelected(String id) {
    MediaControllerCompat.getMediaController(MainActivity.this)
                         .getTransportControls()
                         .playFromMediaId(id, null);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainActivityPresenter = new MainActivityPresenter(this);
    PlaylistManager.setContext(this);
    
    baseMenuFragment = new BaseMenuFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.fl_mainActivity, baseMenuFragment);
    transaction.commit();
    
    swapFragment(new SearchFragment(), true);
    
    playback = findViewById(R.id.cl_playback_mainActivity);
    title = playback.findViewById(R.id.tv_title_playback);
    title.setSelected(true);
    details = playback.findViewById(R.id.tv_secondaryDetails_playback);
    details.setSelected(true);
    back = playback.findViewById(R.id.ib_back_playback);
    playPause = playback.findViewById(R.id.ib_playpause_playback);
    forward = playback.findViewById(R.id.ib_forward_playback);
    
    back.setOnClickListener(this);
    playPause.setOnClickListener(this);
    forward.setOnClickListener(this);
    
    getPerms();
    mainActivityPresenter.refreshTrackList(this);
  }
  
  @Override
  public void onStart() {
    super.onStart();
    
    mediaBrowser =
        new MediaBrowserCompat(
            this,
            new ComponentName(this, PlaybackService.class),
            connectionCallback,
            null);
    mediaBrowser.connect();
  }
  
  @Override
  public void onStop() {
    super.onStop();
    MediaControllerCompat controller = MediaControllerCompat.getMediaController(this);
    if (controller != null) {
      controller.unregisterCallback(mediaControllerCallback);
    }
    if (mediaBrowser != null && mediaBrowser.isConnected()) {
      if (currentMetadata != null) {
        mediaBrowser.unsubscribe(currentMetadata.getString("id"));
      }
      mediaBrowser.disconnect();
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    this.menu = menu;
    return true;
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }
  
  @Override public void swapFragment(BaseFragment fragment, boolean replace) {
    menuFragment = fragment;
    baseMenuFragment.swapFragment(fragment, getSupportFragmentManager());
  }
  
  @Override public void swapTrack(long id, String name, long playlistID, boolean inGroup) {
    this.playlistID = playlistID;
    ConstraintLayout cl = findViewById(R.id.cl_playback_mainActivity);
    if(cl.getVisibility() != View.VISIBLE) {
      cl.setVisibility(View.VISIBLE);
      cl.setAlpha(0.0f);
      cl.animate()
        .translationY(cl.getHeight())
        .alpha(1.0f);
    }
    
    title.setText(name);
    currentID = Long.toString(id);
    playToggle(currentID, playlistID, inGroup, true);
  }
  
  @Override public void updateTitle(String newTitle) {
    setTitle(newTitle);
  }
  
  @Override public void playToggle(final String id, long playlistID, final boolean inGroup, boolean newTrack) {
    final int state =
        currentState == null
        ? PlaybackStateCompat.STATE_NONE
        : currentState.getState();
    if ((state == PlaybackStateCompat.STATE_PAUSED
        || state == PlaybackStateCompat.STATE_STOPPED
        || state == PlaybackStateCompat.STATE_NONE)
    || newTrack) {
      
      if (currentMetadata == null) {
        currentMetadata = PlaybackManager.toMetaData(this, id, null);
        updateMetadata(currentMetadata);
      }
      
      PlaylistManager.initPlaylist(new Callback.InitPlaylistCB() {
        @Override public void onComplete(boolean succeeded, String msg) {
          PlaylistManager.moveHead(Long.parseLong(id), inGroup, new Callback.moveHeadCB() {
            @Override public void onComplete(boolean succeeded, String msg) {
              playPause.setImageDrawable(
                  ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_pause_black_24dp));
              MediaControllerCompat.getMediaController(MainActivity.this)
                                   .getTransportControls()
                                   .playFromMediaId(id, null);
            }
          });
        }
      }, playlistID);
    } else {
      playPause.setImageDrawable(
          ContextCompat.getDrawable(this, R.drawable.ic_play_arrow_black_24dp));
      MediaControllerCompat.getMediaController(MainActivity.this)
                           .getTransportControls()
                           .pause();
    }
    
    updatePlaybackState(currentState);
  }
  
  @Override public void onClick(View view) {
    String id;
    switch (view.getId()) {
      case R.id.ib_back_playback:
        Log.e("asdf", "previous");
        id = Long.toString(PlaylistManager.getPrev().getTrack().getId());
        currentMetadata = PlaybackManager.toMetaData(this, id, null);
        updateMetadata(currentMetadata);
        MediaControllerCompat.getMediaController(MainActivity.this)
                             .getTransportControls()
                             .playFromMediaId(id, null);
        break;
      case R.id.ib_playpause_playback:
        playToggle(currentID, playlistID, false, false);
        break;
      case R.id.ib_forward_playback:
        Log.e("asdf", "next");
        id = Long.toString(PlaylistManager.getNext().getTrack().getId());
        currentMetadata = PlaybackManager.toMetaData(this, id, null);
        updateMetadata(currentMetadata);
        MediaControllerCompat.getMediaController(MainActivity.this)
                             .getTransportControls()
                             .playFromMediaId(id, null);
        break;
      default:
        break;
    }
  }
  
  private void updatePlaybackState(PlaybackStateCompat state) {
    currentState = state;
    if (state == null
        || state.getState() == PlaybackStateCompat.STATE_PAUSED
        || state.getState() == PlaybackStateCompat.STATE_STOPPED) {
      playPause.setImageDrawable(
          ContextCompat.getDrawable(this, R.drawable.ic_play_arrow_black_24dp));
    } else {
      playPause.setImageDrawable(
          ContextCompat.getDrawable(this, R.drawable.ic_pause_black_24dp));
    }
  }
  
  private void updateMetadata(MediaMetadataCompat metadata) {
    currentMetadata = metadata;
    if (metadata != null) {
      title.setText(metadata.getString("name"));
      String detailsText = metadata.getString("artist") + " | "
                           + metadata.getString("album");
      details.setText(detailsText);
    }
  }
  
  private void getPerms() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
           PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
           PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                                     Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        0);
    }
  }
}
