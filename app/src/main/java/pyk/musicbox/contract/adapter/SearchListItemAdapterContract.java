package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.AnyEntity;

public interface SearchListItemAdapterContract {
  interface SearchListItemAdapterView {
    void triggerRefresh();
  }
  
  interface SearchListItemAdapterPresenter {
    AnyEntity getEntityFromList(int i);
    int getItemCount();
    void applyFilters(boolean[] slicers);
    void search(boolean[] slicers, String text);
  }
}
