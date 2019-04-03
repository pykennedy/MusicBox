package pyk.musicbox.contract.callback;

import java.util.ArrayList;

import pyk.musicbox.model.dbobjects.Group;

public interface Callback {
  public interface GroupListCB {
    public void onResponse(ArrayList<Group> groups, boolean succeeded);
  }
}
