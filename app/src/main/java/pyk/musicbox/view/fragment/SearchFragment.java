package pyk.musicbox.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.fragment.SearchFragmentContract;
import pyk.musicbox.presenter.SearchFragmentPresenter;
import pyk.musicbox.view.adapter.SearchListItemAdapter;

public class SearchFragment extends Fragment implements View.OnClickListener {
  private TextView artistSlicer;
  private TextView albumSlicer;
  private TextView trackSlicer;
  private TextView groupSlicer;
  private TextView playlistSlicer;
  
  private SearchListItemAdapter slia = new SearchListItemAdapter(this);
  
  private SearchFragmentPresenter searchFragmentPresenter = new SearchFragmentPresenter();
  
  // { artist , album , track , group , playlist }
  boolean[] slicerStatus = {true, true, true, true, true};
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
    
    artistSlicer = rootView.findViewById(R.id.tv_artistSlicer_fragmentSearch);
    artistSlicer.setOnClickListener(this);
    albumSlicer = rootView.findViewById(R.id.tv_albumSlicer_fragmentSearch);
    albumSlicer.setOnClickListener(this);
    trackSlicer = rootView.findViewById(R.id.tv_trackSlicer_fragmentSearch);
    trackSlicer.setOnClickListener(this);
    groupSlicer = rootView.findViewById(R.id.tv_groupSlicer_fragmentSearch);
    groupSlicer.setOnClickListener(this);
    playlistSlicer = rootView.findViewById(R.id.tv_playlistSlicer_fragmentSearch);
    playlistSlicer.setOnClickListener(this);
    
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentSearch);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(slia);
    
    return rootView;
  }
  
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_artistSlicer_fragmentSearch:
        setSlicer(0);
        break;
      case R.id.tv_albumSlicer_fragmentSearch:
        setSlicer(1);
        break;
      case R.id.tv_trackSlicer_fragmentSearch:
        setSlicer(2);
        break;
      case R.id.tv_groupSlicer_fragmentSearch:
        setSlicer(3);
        break;
      case R.id.tv_playlistSlicer_fragmentSearch:
        setSlicer(4);
        break;
      default:
        break;
    }
    
    searchFragmentPresenter.slicersChanged((SearchFragmentContract.SearchFragment_SLIAP) slia,
                                           slicerStatus);
  }
  
  private void setSlicerLight(int i) {
    switch (i) {
      case 0:
        artistSlicer.setBackgroundColor(
            Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 1:
        albumSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 2:
        trackSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 3:
        groupSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 4:
        playlistSlicer.setBackgroundColor(
            Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      default:
        break;
    }
  }
  
  private void setSlicer(int index) {
    // if all slicers are on, then turn all of except selection
    if (allSlicersAre(true)) {
      for (int i = 0; i < 5; i++) {
        slicerStatus[i] = (i == index) ? true : false;
        setSlicerLight(i);
      }
    } else { // otherwise toggle w/e was selected
      slicerStatus[index] = !slicerStatus[index];
      setSlicerLight(index);
      if (allSlicersAre(false)) {
        for (int i = 0; i < 5; i++) {
          slicerStatus[i] = true;
          setSlicerLight(i);
        }
      }
    }
  }
  
  private boolean allSlicersAre(boolean onOrOff) {
    for (int i = 0; i < 5; i++) {
      if (slicerStatus[i] != onOrOff) {
        return false;
      }
    }
    return true;
  }
}
