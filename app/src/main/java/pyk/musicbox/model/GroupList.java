package pyk.musicbox.model;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.dbobjects.Group;

public class GroupList {
  private static final GroupList   instance = new GroupList();
  private              List<Group> groups;
  
  private GroupList() {
    groups = new ArrayList<>();
  }
  
  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }
  
  public List<Group> getGroups() {
    return groups;
  }
  
  public int getCount() {
    return groups.size();
  }
  
  public static GroupList getInstance() {
    return instance;
  }
}
