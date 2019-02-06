package pyk.musicbox.presenter;

import java.util.List;

import pyk.musicbox.contract.GroupListItemAdapterContract;
import pyk.musicbox.model.GroupList;
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
    // TODO: i'll probably want to track existing groups in a hashmap and only update the
    // TODO:   list as more groups are made
    GroupList.getInstance().setGroups(DBHelper.getGroupList());
    return GroupList.getInstance().getGroups();
  }
  
  @Override public Group getGroupFromList(int index) {
    return GroupList.getInstance().getGroups().get(index);
  }
  
  @Override public int getItemCount() {
    return GroupList.getInstance().getCount();
  }
}
