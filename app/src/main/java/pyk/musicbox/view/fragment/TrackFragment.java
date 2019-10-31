package pyk.musicbox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import pyk.musicbox.R;
import pyk.musicbox.contract.listener.Listener;

public class TrackFragment extends Fragment implements View.OnClickListener {
  private Listener.FragmentListener fragmentListener;
  private Listener.PlaybackControlListener playbackControlListener;
  private String                    viewingName;
  private long                      viewingID; // set to random track so its never empty
  private long                      playingID;
  private String                    playingName;
  private boolean                   viewing = false;
  private boolean                   playing = false;
  private ImageButton               playPause;
  private Context                   context;
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
    
    if (context instanceof Listener.FragmentListener) {
      fragmentListener = (Listener.FragmentListener) context;
    }
    
    if (context instanceof Listener.FragmentListener) {
      playbackControlListener = (Listener.PlaybackControlListener) context;
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_track, container, false);
    playPause = rootView.findViewById(R.id.ib_playpause_fragmentTrack);
    playPause.setOnClickListener(this);
    
    return rootView;
  }
  
  public void updateInfo(boolean viewing, long id, String name) {
    this.viewing = viewing;
    
    if (viewing) {
      viewingID = id;
      viewingName = name;
    } else {
      playingID = id;
      playingName = name;
    }
  }
  
  public String getTitle() {
    String title;
    
    if (viewing) {
      title = viewingName;
    } else if (playing) {
      title = playingName;
    } else {
      title = viewingName;
    }
    
    return title;
  }
  
  @Override public void onClick(View view) {
    playbackControlListener.playToggle(Long.toString(viewingID));
  }
  
  public void setPlayPause(PlaybackStateCompat state) {
    if (state == null
        || state.getState() == PlaybackStateCompat.STATE_PAUSED
        || state.getState() == PlaybackStateCompat.STATE_STOPPED) {
      playPause.setImageDrawable(
          ContextCompat.getDrawable(context, R.drawable.ic_play_arrow_black_24dp));
    } else {
      playPause.setImageDrawable(
          ContextCompat.getDrawable(context, R.drawable.ic_pause_black_24dp));
    }
  }
}
