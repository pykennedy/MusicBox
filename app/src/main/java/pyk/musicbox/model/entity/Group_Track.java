package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_GROUPID;
import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_TABLE;
import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_TRACKID;

@Entity(tableName = GROUP_TRACK_TABLE)
public class Group_Track {
  @ColumnInfo(name = GROUP_TRACK_GROUPID)
  private int groupID;
  
  @ColumnInfo(name = GROUP_TRACK_TRACKID)
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
