package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_GROUPID;
import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_TABLE;
import static pyk.musicbox.model.DBConstants.Group_TrackConstants.GROUP_TRACK_TRACKID;

@Entity(tableName = GROUP_TRACK_TABLE, primaryKeys = {GROUP_TRACK_GROUPID, GROUP_TRACK_TRACKID})
public class Group_Track {
  @ColumnInfo(name = GROUP_TRACK_GROUPID)
  private long groupID;
  
  @ColumnInfo(name = GROUP_TRACK_TRACKID)
  private long trackID;
  
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
}
