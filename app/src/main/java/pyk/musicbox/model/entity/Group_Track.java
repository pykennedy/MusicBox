package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;

@Entity(tableName = "group_track_table"
    , primaryKeys = {"groupID", "trackID"}
    , indices = {@Index(value = {"groupID", "trackID"}, unique = true)})
public class Group_Track {
  @ColumnInfo(name = "groupID")
  private long groupID;
  
  @ColumnInfo(name = "trackID")
  private long trackID;
  
  @ColumnInfo(name = "sortOrder")
  private int sortOrder;
  
  public Group_Track(long groupID, long trackID) {
    this.groupID = groupID;
    this.trackID = trackID;
  }
  
  public long getGroupID() {
    return groupID;
  }
  
  public long getTrackID() {
    return trackID;
  }
  
  public int getSortOrder() { return sortOrder; }
  
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
