package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

@Entity(tableName = "playlist_grouptrack_table"
    , primaryKeys = {"playlistID", "entityID"}
    , indices = {@Index(value = {"playlistID", "entityID"}, unique = true)})
public class Playlist_GroupTrack {
  @ColumnInfo(name = "playlistID")
  private long playlistID;
  
  @ColumnInfo(name = "entityID")
  private long entityID;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public Playlist_GroupTrack(long playlistID, long entityID, @NonNull String entityType) {
    this.playlistID = playlistID;
    this.entityID = entityID;
    this.entityType = entityType;
  }
  
  public long getPlaylistID() {
    return playlistID;
  }
  
  public long getEntityID() {
    return entityID;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}
