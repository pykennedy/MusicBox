package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_ID;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_KEY;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_NAME;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_TABLE;

@Entity(tableName = ALBUM_TABLE)
public class Album {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = ALBUM_ID)
  private int id;
  
  @NonNull
  @ColumnInfo(name = ALBUM_NAME)
  private String name;
  
  @NonNull
  @ColumnInfo(name = ALBUM_KEY)
  private String key;
  
  public Album(int id, @NonNull String name, @NonNull String key) {
    this.id = id;
    this.name = name;
    this.key = key;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
  
  @NonNull public String getAlbumKey() {
    return key;
  }
}
