package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.SortedEntity;

public interface PlaylistListItemAdapterContract {
  interface PlaylistListItemAdapterView {
    void triggerRefresh();
  }
  
  interface PlaylistListItemAdapterPresenter {
    SortedEntity getEntityFromList(int i);
    int getItemCount();
    void getEntitiesInPlaylist(long id);
    void updateSortOrder(long id, int oldSortOrder, int newSortOrder);
    void removeEntity(long playlistID, long entityID, String entityType, int sortOrder);
  }
}
