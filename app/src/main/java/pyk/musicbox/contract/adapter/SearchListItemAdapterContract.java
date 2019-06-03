package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.AllEntities;

public interface SearchListItemAdapterContract {
  interface SearchListItemAdapterView {
    void triggerRefresh();
  }
  
  interface SearchListItemAdapterPresenter {
    AllEntities getEntityFromList(int i);
    int getItemCount();
  }
}
