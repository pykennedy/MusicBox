package pyk.musicbox.contract.model;

import java.util.List;

import pyk.musicbox.model.dbobjects.Group;

public interface DatabaseContract {
  interface DBHelperModel {
    List<Group> getGroupList();
  }
}
