package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_ALBUMID;
import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_TABLE;
import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_TRACKID;


@Entity(tableName = ALBUM_TRACK_TABLE, primaryKeys = {ALBUM_TRACK_ALBUMID, ALBUM_TRACK_TRACKID})
public class Album_Track {
  @ColumnInfo(name = ALBUM_TRACK_ALBUMID)
  private long albumID;
  
  @ColumnInfo(name = ALBUM_TRACK_TRACKID)
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