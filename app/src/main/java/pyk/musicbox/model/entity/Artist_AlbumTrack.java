package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "artist_albumtrack_table",
        primaryKeys = {"artistID", "entityID"})
public class Artist_AlbumTrack {
  @ColumnInfo(name = "artistID")
  private long artistID;
  
  @ColumnInfo(name = "entityID")
  private long entityID;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public Artist_AlbumTrack(long artistID, long entityID, @NonNull String entityType) {
    this.artistID = artistID;
    this.entityID = entityID;
    this.entityType = entityType;
  }
  
  public long getArtistID() {
    return artistID;
  }
  
  public long getEntityID() {
    return entityID;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}
