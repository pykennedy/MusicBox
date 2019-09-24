package pyk.musicbox.contract.fragment;

import java.util.List;

import pyk.musicbox.view.activity.MainActivity;

public interface AddToPlaylistFragmentContract {
  interface AddToPlaylistListItemAdapterView {
    void applyFilters(boolean[] slicers);
  }
  
  interface AddToPlaylistFragmentPresenter {
    void slicersChanged(AddToPlaylistFragmentContract.AddToPlaylistListItemAdapterView adapterView, boolean[] slicers);
    void addToPlaylist(MainActivity context, Long playlistID, List<Long> TrackID, List<Long> GroupID);
  }
}
