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
  private int id;
  
  @NonNull
  @ColumnInfo(name = GROUP_NAME)
  private String name;
  
  public Group(int id, String name) {
    this.id = id;
    this.name = (name == null) ? "Group #" + id : name;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}
