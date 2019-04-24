package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.Artist_AlbumTrackConstants.ARTIST_ALBUMTRACK_ARTISTID;
import static pyk.musicbox.model.DBConstants.Artist_AlbumTrackConstants.ARTIST_ALBUMTRACK_ENTITYID;
import static pyk.musicbox.model.DBConstants.Artist_AlbumTrackConstants.ARTIST_ALBUMTRACK_ENTITYTYPE;
import static pyk.musicbox.model.DBConstants.Artist_AlbumTrackConstants.ARTIST_ALBUMTRACK_TABLE;

@Entity(tableName = ARTIST_ALBUMTRACK_TABLE)
public class Artist_AlbumTrack {
  @ColumnInfo(name = ARTIST_ALBUMTRACK_ARTISTID)
  private int artistID;
  
  @ColumnInfo(name = ARTIST_ALBUMTRACK_ENTITYID)
  private int entityID;
  
  @NonNull
  @ColumnInfo(name = ARTIST_ALBUMTRACK_ENTITYTYPE)
  private String entityType;
  
  public Artist_AlbumTrack(int artistID, int entityID, @NonNull String entityType) {
    this.artistID = artistID;
    this.entityID = entityID;
    this.entityType = entityType;
  }
  
  public int getArtistID() {
    return artistID;
  }
  
  public int getEntityID() {
    return entityID;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}
