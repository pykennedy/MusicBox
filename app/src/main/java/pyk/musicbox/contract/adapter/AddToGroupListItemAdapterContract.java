package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.AnyEntity;

public interface AddToGroupListItemAdapterContract {
  interface AddToGroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface AddToGroupListItemAdapterPresenter {
    AnyEntity getEntityFromList(int i);
    int getItemCount();
    void applyFilters(boolean[] slicers);
    void search(boolean[] slicers, String text);
  }
}
