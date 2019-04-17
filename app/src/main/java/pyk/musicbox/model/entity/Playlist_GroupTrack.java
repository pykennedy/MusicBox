package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "playlist_grouptrack_table")
public class Playlist_GroupTrack {
  @ColumnInfo(name = "playlistID")
  private int playlistID;
  
  @ColumnInfo(name = "trackID")
  private int trackID;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public Playlist_GroupTrack(int playlistID, int trackID, @NonNull String entityType) {
    this.playlistID = playlistID;
    this.trackID = trackID;
    this.entityType = entityType;
  }
  
  public int getPlaylistID() {
    return playlistID;
  }
  
  public int getTrackID() {
    return trackID;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}
