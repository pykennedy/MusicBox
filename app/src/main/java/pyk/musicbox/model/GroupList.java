package pyk.musicbox.model;

import java.util.ArrayList;

import pyk.musicbox.model.entity.Group;

public class GroupList {
  private static final GroupList                  instance = new GroupList();
  private              ArrayList<Group>                groups;
  
  public static GroupList getInstance() {
    return instance;
  }
  
  private GroupList() {
    groups = new ArrayList<>();
  }
  
  public int getCount() {
    return groups.size();
  }
  
  public ArrayList<Group> getGroups() { return groups; }

  public void addAllGroups(ArrayList<Group> groups) {
    this.groups.clear();
    this.groups.addAll(groups);
  }
}
