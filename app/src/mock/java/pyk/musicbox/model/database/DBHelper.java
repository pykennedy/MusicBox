package pyk.musicbox.model.database;

import java.util.concurrent.TimeUnit;

import pyk.musicbox.contract.Listener;
import pyk.musicbox.model.dbobjects.Group;

public class DBHelper {
  private static final DBHelper                        instance = new DBHelper();
  private              Listener.GroupListTableListener groupListTableListener;
  
  public static DBHelper getInstance() {
    return instance;
  }
  
  public void setGroupListTableListener(Listener.GroupListTableListener listener) {
    this.groupListTableListener = listener;
  }
  
  public void populateGroupList() {
    for (int i = 0; i < 20; i++) {
      // for testing purposes, remove this wait
      try {
        TimeUnit.SECONDS.sleep(2);
        groupListTableListener.onInsert(new Group(i, "Group #" + i));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      
    }
  }
}
