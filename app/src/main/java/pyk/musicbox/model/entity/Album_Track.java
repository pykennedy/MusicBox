package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_ALBUMID;
import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_TABLE;
import static pyk.musicbox.model.DBConstants.Album_TrackConstants.ALBUM_TRACK_TRACKID;


@Entity(tableName = ALBUM_TRACK_TABLE)
public class Album_Track {
  @ColumnInfo(name = ALBUM_TRACK_ALBUMID)
  private int albumID;
  
  @ColumnInfo(name = ALBUM_TRACK_TRACKID)
  private int trackID;
  
  public Album_Track(int albumID, int trackID) {
    this.albumID = albumID;
    this.trackID = trackID;
  }
  
  public int getAlbumID() {
    return albumID;
  }
  
  public int getTrackID() {
    return trackID;
  }
}