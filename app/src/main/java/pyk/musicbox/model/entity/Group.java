package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.GroupConstants.GROUP_NAME;
import static pyk.musicbox.model.DBConstants.GroupConstants.GROUP_TABLE;

@Entity(tableName = GROUP_TABLE)
public class Group extends Base {
  @NonNull
  @ColumnInfo(name = GROUP_NAME)
  private String name;
  
  public Group(@NonNull String name) {
    this.name = name;
  }
  
  @Override
  public int getId() {
    return super.getId();
  }
  
  @NonNull
  public String getName() {
    return name;
  }
}
