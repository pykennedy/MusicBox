package pyk.musicbox.support;

import java.util.ArrayList;

import pyk.musicbox.model.entity.Group;

public class StaticValues {
  public static final ArrayList<Group> groupList = buildGroupList();
  
  private static ArrayList<Group> buildGroupList() {
    ArrayList<Group> groups = new ArrayList<>();
    for(int i = 0; i < 20; i++) {
      groups.add(new Group(i, "Group #" + i));
    }
    return groups;
  }
}
