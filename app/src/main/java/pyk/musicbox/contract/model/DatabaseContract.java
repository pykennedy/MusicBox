package pyk.musicbox.contract.model;

import java.util.List;

import pyk.musicbox.model.entity.Group;

public interface DatabaseContract {
  interface DBHelperModel {
    List<Group> getGroupList();
  }
}
