package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;


@Entity(tableName = "album_track_table"
    , primaryKeys = {"albumID", "trackID"}
    , indices = {@Index(value = {"albumID", "trackID"}, unique = true)})
public class Album_Track {
  @ColumnInfo(name = "albumID")
  private long albumID;
  
  @ColumnInfo(name = "trackID")
  private long trackID;
  
  public Album_Track(long albumID, long trackID) {
    this.albumID = albumID;
    this.trackID = trackID;
  }
  
  public long getAlbumID() {
    return albumID;
  }
  
  public long getTrackID() {
    return trackID;
  }
}