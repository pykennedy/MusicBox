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
  private long id;
  
  @NonNull
  @ColumnInfo(name = ARTIST_NAME)
  private String name;
  
  public Artist(@NonNull String name) {
    this.name = name;
  }
  
  public long getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}
