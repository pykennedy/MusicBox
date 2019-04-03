package pyk.musicbox.model.database;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.support.StaticValues;

public class DBHelper {
  private static final DBHelper                        instance = new DBHelper();
  
  public static DBHelper getInstance() {
    return instance;
  }
  
  public void populateGroupList(Callback.GroupListCB groupListCB) {
    groupListCB.onResponse(StaticValues.groupList, true);
  }
}
