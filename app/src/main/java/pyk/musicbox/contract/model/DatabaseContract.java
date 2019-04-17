package pyk.musicbox.contract.model;

import java.util.List;

import pyk.musicbox.model.entity.GroupOld;

public interface DatabaseContract {
  interface DBHelperModel {
    List<GroupOld> getGroupList();
  }
}
