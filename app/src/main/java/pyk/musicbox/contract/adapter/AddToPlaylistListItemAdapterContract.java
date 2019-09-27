package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.AnyEntity;

public interface AddToPlaylistListItemAdapterContract {
  interface AddToPlaylistListItemAdapterView {
    void triggerRefresh();
  }
  
  interface AddToPlaylistListItemAdapterPresenter {
    AnyEntity getEntityFromList(int i);
    int getItemCount();
    void applyFilters(boolean[] slicers);
  }
}
