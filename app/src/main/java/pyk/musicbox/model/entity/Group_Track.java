package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "group_track_table")
public class Group_Track {
  @ColumnInfo(name = "groupID")
  private int groupID;
  
  @ColumnInfo(name = "trackID")
  private int trackID;
  
  public Group_Track(int groupID, int trackID) {
    this.groupID = groupID;
    this.trackID = trackID;
  }
  
  public int getGroupID() {
    return groupID;
  }
  
  public int getTrackID() {
    return trackID;
  }
}
