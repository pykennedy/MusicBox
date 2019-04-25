package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.ArtistConstants.ARTIST_NAME;
import static pyk.musicbox.model.DBConstants.ArtistConstants.ARTIST_TABLE;

@Entity(tableName = ARTIST_TABLE)
public class Artist extends Base {
  
  @NonNull
  @ColumnInfo(name = ARTIST_NAME)
  private String name;
  
  public Artist(@NonNull String name) {
    this.name = name;
  }
  
  public int getId() {
    return super.getId();
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}
