package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.SortedTrack;

public interface AlbumListItemAdapterContract {
  interface AlbumListItemAdapterView {
    void triggerRefresh();
  }
  
  interface  AlbumListItemAdapterPresenter {
    SortedTrack getTrackFromList(int i);
    int getItemCount();
    void getTracksInAlbum(long id);
  }
}
