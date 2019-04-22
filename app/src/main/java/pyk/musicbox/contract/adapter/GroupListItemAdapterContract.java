package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.Group;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface GroupListItemAdapterPresenter {
    void populateGroupList();
    Group getGroupFromList(int index);
    int getItemCount();
  }
}
