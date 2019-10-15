package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.SortedEntity;

public interface ArtistListItemAdapterContract {
  interface ArtistListItemAdapterView {
    void triggerRefresh();
  }
  
  interface ArtistListItemAdapterPresenter {
    SortedEntity getEntityFromList(int i);
    int getItemCount();
    void getEntitiesInArist(long id);
  }
}
