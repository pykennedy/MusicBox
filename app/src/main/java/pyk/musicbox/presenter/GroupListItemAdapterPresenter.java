package pyk.musicbox.presenter;

import java.util.List;

import pyk.musicbox.contract.GroupListItemAdapterContract;
import pyk.musicbox.model.database.DBHelper;
import pyk.musicbox.model.dbobjects.Group;

public class GroupListItemAdapterPresenter
    implements GroupListItemAdapterContract.GroupListItemAdapterPresenter {
  GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  
  public GroupListItemAdapterPresenter(GroupListItemAdapterContract.GroupListItemAdapterView gliav) {
    this.gliav = gliav;
  }
  
  @Override
  public List<Group> getGroupsFromDB() {
    return DBHelper.getGroupList();
  }
}
