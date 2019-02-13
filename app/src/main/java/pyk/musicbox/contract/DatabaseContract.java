package pyk.musicbox.contract;

import java.util.List;

import pyk.musicbox.model.dbobjects.Group;

public interface DatabaseContract {
  interface DBHelperModel {
    List<Group> getGroupList();
  }
}
