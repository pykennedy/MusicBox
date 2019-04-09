package pyk.musicbox.model.database;

import pyk.musicbox.contract.callback.Callback;

public class DBHelper {
  private static final DBHelper                        instance = new DBHelper();
  
  public static DBHelper getInstance() {
    return instance;
  }
  
  public void populateGroupList(Callback.GroupListCB groupListCB) {
  }
}
