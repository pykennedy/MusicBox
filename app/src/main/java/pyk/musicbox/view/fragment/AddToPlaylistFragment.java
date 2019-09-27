package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.presenter.AddToPlaylistFragmentPresenter;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.AddToPlaylistListItemAdapter;

public class AddToPlaylistFragment extends Fragment implements View.OnClickListener {
  private long                           playlistID;
  private TextView                       trackSlicer;
  private TextView                       groupSlicer;
  private FloatingActionButton           fab;
  private AddToPlaylistListItemAdapter   adapter;
  private AddToPlaylistFragmentPresenter presenter;
  
  boolean[] slicerStatus = {false, false, true, true, false};
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_addtoplaylist, container,
                                                      false);
    
    trackSlicer = rootView.findViewById(R.id.tv_trackSlicer_fragmentAddToPlaylist);
    trackSlicer.setOnClickListener(this);
    groupSlicer = rootView.findViewById(R.id.tv_playlistSlicer_fragmentAddToPlaylist);
    groupSlicer.setOnClickListener(this);
    fab = rootView.findViewById(R.id.fab_addButton_fragmentAddToPlaylist);
    fab.setOnClickListener(this);
    
    presenter = new AddToPlaylistFragmentPresenter();
    
    Bundle args = getArguments();
    if(args != null) {
      playlistID = args.getLong("id");
    }
    
    adapter = new AddToPlaylistListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentAddToPlaylist);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    
    presenter.slicersChanged(adapter, slicerStatus);
    
    return rootView;
  }
  
  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.fab_addButton_fragmentAddToPlaylist:
        presenter.addToPlaylist((MainActivity) getActivity(), playlistID, adapter.getSelectedEntityIDs());
        getActivity().onBackPressed();
        break;
      default:
        break;
    }
  }
}
