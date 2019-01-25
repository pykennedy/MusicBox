package pyk.musicbox.model.database;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.dbobjects.Group;

public class DBHelper{
  public static List<Group> getGroupList() {
    ArrayList<Group> groups = new ArrayList<>();
    for(int i = 0; i < 20; i++) {
      groups.add(new Group(i, "Group #" + i));
    }
    return groups;
  }
}
