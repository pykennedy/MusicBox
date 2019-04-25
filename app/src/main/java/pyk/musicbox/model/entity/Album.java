package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_ARTIST;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_KEY;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_NAME;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_TABLE;

@Entity(tableName = ALBUM_TABLE
        , indices = {@Index(value = {ALBUM_KEY}, unique = true)})
public class Album extends Base {
  @NonNull
  @ColumnInfo(name = ALBUM_NAME)
  private String name;
  
  @NonNull
  @ColumnInfo(name = ALBUM_ARTIST)
  private String artist;
  
  @NonNull
  @ColumnInfo(name = ALBUM_KEY)
  private String key;
  
  public Album(@NonNull String name, @NonNull String artist, @NonNull String key) {
    this.name = name;
    this.artist = artist;
    this.key = key;
  }
  
  public int getId() {
    return super.getId();
  }
  
  @NonNull
  public String getName() {
    return name;
  }
  
  @NonNull public String getKey() {
    return key;
  }
  
  @NonNull public String getArtist() {
    return artist;
  }
}
