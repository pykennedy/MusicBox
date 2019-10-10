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
  private String                    name;
  private Long                      id;
  
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
  
  public void updateInfo() {
    if(getArguments() != null) {
      name = getArguments().getString("trackName");
      id = getArguments().getLong("id");
      listener.updateTitle(name != null ? name : "Error Retrieving Track Name");
    }
  }
}
