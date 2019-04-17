package pyk.musicbox.presenter;

import java.util.ArrayList;

import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.GroupList;
import pyk.musicbox.model.database.DBHelper;
import pyk.musicbox.model.entity.GroupOld;

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
      @Override public void onResponse(ArrayList<GroupOld> groupOlds, boolean succeeded) {
        if(succeeded) {
          groupList.addAllGroups(groupOlds);
          gliav.triggerRefresh();
        } else {
          // TODO: error handling
        }
      }
    });
  }
  
  @Override public GroupOld getGroupFromList(int index) {
    return groupList.getGroupOlds().get(index);
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
