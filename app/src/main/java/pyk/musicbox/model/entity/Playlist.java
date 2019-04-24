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
  private int id;
  
  @NonNull
  @ColumnInfo(name = PLAYLIST_NAME)
  private String name;
  
  public Playlist(int id, String name) {
    this.id = id;
    this.name = (name == null) ? "Playlist #" + id : name;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}