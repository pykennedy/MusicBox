package pyk.musicbox.presenter;

import java.util.List;

import pyk.musicbox.contract.GroupListItemAdapterContract;
import pyk.musicbox.contract.Listener;
import pyk.musicbox.model.GroupList;
import pyk.musicbox.model.dbobjects.Group;

public class GroupListItemAdapterPresenter
    implements GroupListItemAdapterContract.GroupListItemAdapterPresenter, Listener.GroupListListener {
  private GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  
  public GroupListItemAdapterPresenter(GroupListItemAdapterContract.GroupListItemAdapterView gliav) {
    this.gliav = gliav;
    GroupList.getInstance().setGroupListListener(this);
  }
  
  @Override
  public List<Group> getGroupsFromDB() {
    // TODO: i'll probably want to track existing groups in a hashmap and only update the
    // TODO:   list as more groups are made
    return GroupList.getInstance().getGroups();
  }
  
  @Override public Group getGroupFromList(int index) {
    return GroupList.getInstance().getGroups().get(index);
  }
  
  @Override public int getItemCount() {
    return GroupList.getInstance().getCount();
  }
  
  @Override public void listUpdated() {
    gliav.triggerRefresh();
  }
}
