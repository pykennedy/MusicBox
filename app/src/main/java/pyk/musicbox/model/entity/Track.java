package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "track_table")
public class Track {
  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "id")
  private int id;
  
  @NonNull
  @ColumnInfo(name = "name")
  private String name;
  
  @ColumnInfo(name = "album")
  private String album;
  
  @ColumnInfo(name = "artist")
  private String artist;
  
  @ColumnInfo(name = "duration")
  private String duration;
  
  public Track(@NonNull int id, @NonNull String name) {
    this.id = id;
    this.name = name;
  }
  
  public int getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public String getAlbum() {
    return album;
  }
  
  public String getArtist() {
    return artist;
  }
  
  public String getDuration() {
    return duration;
  }
}
