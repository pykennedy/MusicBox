package pyk.musicbox.model;

import java.util.ArrayList;

import pyk.musicbox.model.entity.GroupOld;

public class GroupList {
  private static final GroupList           instance = new GroupList();
  private              ArrayList<GroupOld> groupOlds;
  
  public static GroupList getInstance() {
    return instance;
  }
  
  private GroupList() {
    groupOlds = new ArrayList<>();
  }
  
  public int getCount() {
    return groupOlds.size();
  }
  
  public ArrayList<GroupOld> getGroupOlds() { return groupOlds; }

  public void addAllGroups(ArrayList<GroupOld> groupOlds) {
    this.groupOlds.clear();
    this.groupOlds.addAll(groupOlds);
  }
}
