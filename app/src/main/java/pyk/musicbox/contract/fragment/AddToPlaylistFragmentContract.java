package pyk.musicbox.contract.fragment;

import java.util.List;

import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.view.activity.MainActivity;

public interface AddToPlaylistFragmentContract {
  interface AddToPlaylistListItemAdapterView {
    void applyFilters(boolean[] slicers);
    void search(boolean[] slicers, String text);
  }
  
  interface AddToPlaylistFragmentPresenter {
    void slicersChanged(AddToPlaylistListItemAdapterView adapterView, boolean[] slicers);
    void addToPlaylist(MainActivity context, Long playlistID, List<AnyEntity> entities);
    void search(AddToPlaylistListItemAdapterView adapter, boolean[] slicers, String text);
  }
}
