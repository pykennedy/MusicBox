package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "artist_table")
public class Artist {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private long id;
  
  @NonNull
  @ColumnInfo(name = "name")
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
  
  public void setId(long id) {
    this.id = id;
  }
}
