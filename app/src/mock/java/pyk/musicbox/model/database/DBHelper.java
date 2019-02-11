package pyk.musicbox.model.database;

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
      
      ThreadManager.getInstance().runTask(new Runnable() {
        @Override public void run() {
          //TODO: db calls
          
          /*
          // wait for testing purposes
          try {
            TimeUnit.MILLISECONDS.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } */
          ThreadManager.getInstance().runTask(new Runnable() {
            @Override public void run() {
              groupListTableListener.onInsert(new Group(j, "Group #" + j));
            }
          }, true);
        }
      }, false);
    }
  }
}
