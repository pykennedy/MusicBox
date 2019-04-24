package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.ArtistConstants.ARTIST_ID;
import static pyk.musicbox.model.DBConstants.ArtistConstants.ARTIST_NAME;
import static pyk.musicbox.model.DBConstants.ArtistConstants.ARTIST_TABLE;

@Entity(tableName = ARTIST_TABLE)
public class Artist {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = ARTIST_ID)
  private int id;
  
  @NonNull
  @ColumnInfo(name = ARTIST_NAME)
  private String name;
  
  public Artist(int id, String name) {
    this.id = id;
    this.name = (name == null) ? "Artist #" + id : name;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}
