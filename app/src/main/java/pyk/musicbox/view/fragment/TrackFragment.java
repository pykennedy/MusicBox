package pyk.musicbox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.listener.Listener;

public class TrackFragment extends Fragment {
  private Listener.FragmentListener listener;
  private String                    viewingName;
  private long                      viewingID; // set to random track so its never empty
  private long                      playingID;
  private String                    playingName;
  private boolean                   viewing = false;
  private boolean                   playing = false;
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    
    if (context instanceof Listener.FragmentListener) {
      listener = (Listener.FragmentListener) context;
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_track, container, false);
    
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
    
    if(viewing) {
      title = viewingName;
    } else if(playing) {
      title = playingName;
    } else {
      title = viewingName;
    }
    
    return title;
  }
}
