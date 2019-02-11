package pyk.musicbox.model.database;

import java.util.concurrent.TimeUnit;

import pyk.musicbox.contract.Listener;
import pyk.musicbox.model.dbobjects.Group;
import pyk.musicbox.utility.ThreadManager;

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
      final int j = i;
      // for testing purposes, remove this wait
      ThreadManager.getInstance().runTaskWithUICallback(
          new Runnable() {
            @Override public void run() {
              try {
                TimeUnit.MILLISECONDS.sleep(500);
                groupListTableListener.onInsert(new Group(j, "Group #" + j));
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }, new Runnable() {
            @Override public void run() {
            
            }
          });
      
      /*
      try {
        TimeUnit.MILLISECONDS.sleep(100);
        groupListTableListener.onInsert(new Group(i, "Group #" + i));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } */
    }
  }
}
