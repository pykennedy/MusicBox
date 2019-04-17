package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.GroupOld;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface GroupListItemAdapterPresenter {
    void populateGroupList();
    GroupOld getGroupFromList(int index);
    int getItemCount();
  }
}
