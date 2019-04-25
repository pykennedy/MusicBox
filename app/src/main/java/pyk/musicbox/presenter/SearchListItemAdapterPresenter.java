package pyk.musicbox.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.AlbumViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapterPresenter implements SearchListItemAdapterContract.SearchListItemAdapterPresenter {
  SearchListItemAdapterContract.SearchListItemAdapterView sliav;
  private TrackViewModel tvm;
  private AlbumViewModel avm;
  
  private List<Track> tracks = new ArrayList<>();
  private List<Album> albums = new ArrayList<>();
  
  public SearchListItemAdapterPresenter(final SearchListItemAdapterContract.SearchListItemAdapterView sliav, SearchFragment context) {
    this.sliav = sliav;
    tvm = ViewModelProviders.of(context).get(TrackViewModel.class);
    tvm.getAllTracks().observe(context, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> trackList) {
        tracks = trackList;
        for(Track track : tracks) {
          Log.e("track", track.getId() + "     " + track.getName());
        }
        sliav.triggerRefresh();
      }
    });
    
    avm = ViewModelProviders.of(context).get(AlbumViewModel.class);
    avm.getAllAlbums().observe(context, new Observer<List<Album>>() {
      @Override public void onChanged(@Nullable List<Album> albumList) {
        albums = albumList;
        for(Album album : albums) {
          Log.e("album", album.getId() + "     " + album.getName());
        }
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
