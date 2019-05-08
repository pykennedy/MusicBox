package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.PlaylistConstants.PLAYLIST_ID;
import static pyk.musicbox.model.DBConstants.PlaylistConstants.PLAYLIST_NAME;
import static pyk.musicbox.model.DBConstants.PlaylistConstants.PLAYLIST_TABLE;

@Entity(tableName = PLAYLIST_TABLE)
public class Playlist {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = PLAYLIST_ID)
  private long id;
  
  @NonNull
  @ColumnInfo(name = PLAYLIST_NAME)
  private String name;
  
  public Playlist(String name) {
    this.name = name;
  }
  
  public long getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
  
  public void setId(long id) {
    this.id = id;
  }
}