package pyk.musicbox.presenter;

import java.util.ArrayList;

import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.GroupList;
import pyk.musicbox.model.database.DBHelper;
import pyk.musicbox.model.dbobjects.Group;

public class GroupListItemAdapterPresenter
    implements GroupListItemAdapterContract.GroupListItemAdapterPresenter {
  
  private GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  private GroupList groupList;
  private DBHelper dbHelper;
  
  public GroupListItemAdapterPresenter(GroupListItemAdapterContract.GroupListItemAdapterView gliav) {
    this.gliav = gliav;
    this.groupList = GroupList.getInstance();
    this.dbHelper = DBHelper.getInstance();
  }
  
  @Override
  public void populateGroupList() {
    if(groupList.getCount() > 0) {
      gliav.triggerRefresh();
    }
    
    dbHelper.populateGroupList(new Callback.GroupListCB() {
      @Override public void onResponse(ArrayList<Group> groups, boolean succeeded) {
        if(succeeded) {
          groupList.addAllGroups(groups);
          gliav.triggerRefresh();
        } else {
          // TODO: error handling
        }
      }
    });
  }
  
  @Override public Group getGroupFromList(int index) {
    return groupList.getGroups().get(index);
  }
  
  @Override public int getItemCount() {
    return groupList.getCount();
  }
  
  /****************************************************************************************
   dependency injection for testing, no production code allowed beyond this point
   ***************************************************************************************/
  
  public GroupListItemAdapterPresenter(GroupListItemAdapterContract.GroupListItemAdapterView gliav, GroupList groupList, DBHelper dbHelper) {
    this.gliav = gliav;
    this.groupList = groupList;
    this.dbHelper = dbHelper;
  }
}
