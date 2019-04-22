package pyk.musicbox.model;

import java.util.ArrayList;

import pyk.musicbox.model.entity.Group;

public class GroupList {
  private static final GroupList        instance = new GroupList();
  private              ArrayList<Group> groupOlds;
  
  public static GroupList getInstance() {
    return instance;
  }
  
  private GroupList() {
    groupOlds = new ArrayList<Group>();
  }
  
  public int getCount() {
    return groupOlds.size();
  }
  
  public ArrayList<Group> getGroupOlds() { return groupOlds; }

  public void addAllGroups(ArrayList<Group> groupOlds) {
    this.groupOlds.clear();
    this.groupOlds.addAll(groupOlds);
  }
}
