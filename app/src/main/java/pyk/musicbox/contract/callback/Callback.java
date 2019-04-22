package pyk.musicbox.contract.callback;

import java.util.ArrayList;

import pyk.musicbox.model.entity.Group;

public interface Callback {
  public interface GroupListCB {
    public void onResponse(ArrayList<Group> groupOlds, boolean succeeded);
  }
}
