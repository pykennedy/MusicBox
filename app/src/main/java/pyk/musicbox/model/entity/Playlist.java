package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.PlaylistConstants.PLAYLIST_NAME;
import static pyk.musicbox.model.DBConstants.PlaylistConstants.PLAYLIST_TABLE;

@Entity(tableName = PLAYLIST_TABLE)
public class Playlist extends Base {
  
  @NonNull
  @ColumnInfo(name = PLAYLIST_NAME)
  private String name;
  
  public Playlist(String name) {
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