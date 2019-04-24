package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.Playlist_GroupTrackConstants.PLAYLIST_GROUPTRACK_ENTITYID;
import static pyk.musicbox.model.DBConstants.Playlist_GroupTrackConstants.PLAYLIST_GROUPTRACK_ENTITYTYPE;
import static pyk.musicbox.model.DBConstants.Playlist_GroupTrackConstants.PLAYLIST_GROUPTRACK_PLAYLISTID;
import static pyk.musicbox.model.DBConstants.Playlist_GroupTrackConstants.PLAYLIST_GROUPTRACK_TABLE;

@Entity(tableName = PLAYLIST_GROUPTRACK_TABLE)
public class Playlist_GroupTrack {
  @ColumnInfo(name = PLAYLIST_GROUPTRACK_PLAYLISTID)
  private int playlistID;
  
  @ColumnInfo(name = PLAYLIST_GROUPTRACK_ENTITYID)
  private int entityID;
  
  @NonNull
  @ColumnInfo(name = PLAYLIST_GROUPTRACK_ENTITYTYPE)
  private String entityType;
  
  public Playlist_GroupTrack(int playlistID, int entityID, @NonNull String entityType) {
    this.playlistID = playlistID;
    this.entityID = entityID;
    this.entityType = entityType;
  }
  
  public int getPlaylistID() {
    return playlistID;
  }
  
  public int getEntityID() {
    return entityID;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}
