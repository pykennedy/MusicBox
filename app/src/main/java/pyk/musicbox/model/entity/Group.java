package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.GroupConstants.GROUP_ID;
import static pyk.musicbox.model.DBConstants.GroupConstants.GROUP_NAME;
import static pyk.musicbox.model.DBConstants.GroupConstants.GROUP_TABLE;

@Entity(tableName = GROUP_TABLE)
public class Group {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = GROUP_ID)
  private long id;
  
  @NonNull
  @ColumnInfo(name = GROUP_NAME)
  private String name;
  
  public Group(@NonNull String name) {
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
