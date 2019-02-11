package pyk.musicbox.model;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.contract.Listener;
import pyk.musicbox.model.database.DBHelper;
import pyk.musicbox.model.dbobjects.Group;
import pyk.musicbox.utility.ThreadManager;

public class GroupList
    implements Listener.GroupListTableListener {
  private static final GroupList                  instance = new GroupList();
  private              List<Group>                groups;
  private              Listener.GroupListListener groupListListener;
  
  public void setGroupListListener(Listener.GroupListListener listener) {
    this.groupListListener = listener;
  }
  
  private GroupList() {
    groups = new ArrayList<>();
    DBHelper.getInstance().setGroupListTableListener(this);
  }
  
  public List<Group> getGroups() {
    if (groups.size() == 0) {
      DBHelper.getInstance().populateGroupList();
    }
    
    return groups;
  }
  
  public int getCount() {
    return groups.size();
  }
  
  public static GroupList getInstance() {
    return instance;
  }
  
  @Override public void onInsert(final Group group) {
    // TODO: sort while adding (use add(index i, object))
    ThreadManager.getInstance().runTask(new Runnable() {
      @Override public void run() {
        groups.add(group);
        ThreadManager.getInstance().runTask(new Runnable() {
          @Override public void run() {
            groupListListener.listUpdated();
          }
        }, true);
      }
    }, false);
  }
  
  @Override public void onUpdate() {
  
  }
  
  @Override public void onDelete() {
  
  }
}
