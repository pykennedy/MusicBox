package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "group_table")
public class Group {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int id;
  
  @NonNull
  @ColumnInfo(name = "name")
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
