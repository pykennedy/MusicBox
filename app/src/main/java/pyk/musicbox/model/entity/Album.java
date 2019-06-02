package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "album_table"
    , indices = {@Index(value = {"key"}, unique = true)})
public class Album {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private long id;
  
  @NonNull
  @ColumnInfo(name = "name")
  private String name;
  
  @NonNull
  @ColumnInfo(name = "artist")
  private String artist;
  
  @NonNull
  @ColumnInfo(name = "key")
  private String key;
  
  public Album(@NonNull String name, @NonNull String artist, @NonNull String key) {
    this.name = name;
    this.artist = artist;
    this.key = key;
  }
  
  public long getId() {
    return id;
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
  
  public void setId(long id) {
    this.id = id;
  }
}
