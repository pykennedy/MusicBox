package pyk.musicbox.model.dbobjects;

import java.util.ArrayList;

public class Group {
  private int groupID;
  private String groupTitle;
  private ArrayList<GroupItem> groupItems;
  
  public Group(int groupID, String groupTitle) {
    this.groupID = groupID;
    this.groupTitle = groupTitle;
  }
  
  public int getGroupID() {
    return groupID;
  }
  
  public String getGroupTitle() {
    return groupTitle;
  }
  
  public ArrayList<GroupItem> getGroupItems() {
    return groupItems;
  }
}
