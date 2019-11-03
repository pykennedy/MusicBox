package pyk.musicbox.view.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
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
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.service.PlaybackManager;
import pyk.musicbox.service.PlaybackService;
import pyk.musicbox.view.fragment.BaseMenuFragment;
import pyk.musicbox.view.fragment.TrackFragment;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView, Listener.FragmentListener, Listener.PlaybackControlListener, View.OnClickListener {
  
  private MainActivityPresenter     mainActivityPresenter;
  private ViewPager                 pager;
  private FragmentStatePagerAdapter pagerAdapter;
  private TrackFragment             trackFragment;
  private BaseFragment              menuFragment;
  private Menu                      menu;
  private ViewGroup                 playback;
  private TextView                  title;
  private TextView                  details;
  private ImageButton               back;
  private ImageButton               playPause;
  private ImageButton               forward;
  private SeekBar                   seekBar;
  private String currentID;
  
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
    
    playback = findViewById(R.id.cl_playback_mainActivity);
    title = playback.findViewById(R.id.tv_title_playback);
    details = playback.findViewById(R.id.tv_secondaryDetails_playback);
    back = playback.findViewById(R.id.ib_back_playback);
    playPause = playback.findViewById(R.id.ib_playpause_playback);
    forward = playback.findViewById(R.id.ib_forward_playback);
    
    back.setOnClickListener(this);
    playPause.setOnClickListener(this);
    forward.setOnClickListener(this);
    
    
    pager = findViewById(R.id.vp_mainActivity);
    pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    pager.setAdapter(pagerAdapter);
    pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        if (position == 1) {
          updateTitle(trackFragment.getTitle());
          menu.findItem(R.id.sv_menu).setVisible(false);
        } else {
          if (menuFragment == null) {
            updateTitle("Music Box");
          } else {
            updateTitle(menuFragment.desiredTitle);
          }
          menu.findItem(R.id.sv_menu).setVisible(true);
          ((SearchView) menu.findItem(R.id.sv_menu).getActionView()).setQuery("", false);
          ((SearchView) menu.findItem(R.id.sv_menu).getActionView()).setIconified(true);
        }
      }
    });
    
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
    if (replace) {
      BaseMenuFragment baseMenuFragment = (BaseMenuFragment) pagerAdapter.getItem(0);
      baseMenuFragment.swapFragment(fragment, getSupportFragmentManager());
    } else {
      pager.setCurrentItem(1);
    }
  }
  
  @Override public void swapTrack(long id, String name) {
    title.setText(name);
    currentID = Long.toString(id);
    playToggle(currentID);
    // todo: set other details
  }
  
  @Override public void updateTitle(String newTitle) {
    setTitle(newTitle);
  }
  
  @Override public void playToggle(String id) {
    final int state =
        currentState == null
        ? PlaybackStateCompat.STATE_NONE
        : currentState.getState();
    if (state == PlaybackStateCompat.STATE_PAUSED
        || state == PlaybackStateCompat.STATE_STOPPED
        || state == PlaybackStateCompat.STATE_NONE) {
      
      if (currentMetadata == null) {
        currentMetadata = PlaybackManager.toMetaData(this, id);
        updateMetadata(currentMetadata);
      }
  
      playPause.setImageDrawable(
          ContextCompat.getDrawable(this, R.drawable.ic_pause_black_24dp));
      MediaControllerCompat.getMediaController(MainActivity.this)
                           .getTransportControls()
                           .playFromMediaId(id, null);
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
    switch (view.getId()) {
      case R.id.ib_back_playback:
        break;
      case R.id.ib_playpause_playback:
        playToggle(currentID);
        break;
      case R.id.ib_forward_playback:
        break;
      default:
        break;
    }
  }
  
  private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new BaseMenuFragment();
        case 1:
          trackFragment = (trackFragment == null) ? new TrackFragment() : trackFragment;
          return trackFragment;
        default:
          return new BaseMenuFragment();
      }
    }
    
    @Override
    public int getCount() {
      return 2;
    }
  }
  
  @Override public void onBackPressed() {
    if (pager.getCurrentItem() == 1) {
      pager.setCurrentItem(0);
    } else {
      super.onBackPressed();
    }
  }
  
  private void updatePlaybackState(PlaybackStateCompat state) {
    currentState = state;
    trackFragment.setPlayPause(state);
  }
  
  private void updateMetadata(MediaMetadataCompat metadata) {
    currentMetadata = metadata;
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
