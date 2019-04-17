package pyk.musicbox.contract.callback;

import java.util.ArrayList;

import pyk.musicbox.model.entity.GroupOld;

public interface Callback {
  public interface GroupListCB {
    public void onResponse(ArrayList<GroupOld> groupOlds, boolean succeeded);
  }
}
