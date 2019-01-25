package pyk.musicbox.contract;

import java.util.List;

import pyk.musicbox.model.dbobjects.Group;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface GroupListItemAdapterPresenter {
    List<Group> getGroupsFromDB();
  }
}
