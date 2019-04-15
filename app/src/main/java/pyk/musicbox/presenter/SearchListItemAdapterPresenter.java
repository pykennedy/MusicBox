package pyk.musicbox.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapterPresenter implements SearchListItemAdapterContract.SearchListItemAdapterPresenter {
  SearchListItemAdapterContract.SearchListItemAdapterView sliav;
  private TrackViewModel tvm;
  
  private List<Track> tracks = new ArrayList<>();
  
  public SearchListItemAdapterPresenter(final SearchListItemAdapterContract.SearchListItemAdapterView sliav, SearchFragment context) {
    this.sliav = sliav;
    tvm = ViewModelProviders.of(context).get(TrackViewModel.class);
    tvm.getAllTracks().observe(context, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> trackList) {
        tracks = trackList;
        sliav.triggerRefresh();
      }
    });
  }
  
  @Override public Track getTrackFromList(int i) {
    return tracks.get(i);
  }
  
  @Override public int getItemCount() {
    return tracks.size();
  }
}
