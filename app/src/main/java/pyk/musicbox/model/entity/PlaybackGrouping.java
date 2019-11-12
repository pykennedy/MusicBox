package pyk.musicbox.model.entity;

import android.arch.persistence.room.Embedded;

public class PlaybackGrouping {
  private long groupID;
  private String groupName;
  private int sortOrder;
  @Embedded
  private Track track;
  
  public long getGroupID() {
    return groupID;
  }
  
  public void setGroupID(long groupID) {
    this.groupID = groupID;
  }
  
  public String getGroupName() {
    return groupName;
  }
  
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  
  public int getSortOrder() {
    return sortOrder;
  }
  
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
  
  public Track getTrack() {
    return track;
  }
  
  public void setTrack(Track track) {
    this.track = track;
  }
}
