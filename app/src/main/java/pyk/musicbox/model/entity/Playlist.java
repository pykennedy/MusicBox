package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "playlist_table")
public class Playlist {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int id;
  
  @NonNull
  @ColumnInfo(name = "name")
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