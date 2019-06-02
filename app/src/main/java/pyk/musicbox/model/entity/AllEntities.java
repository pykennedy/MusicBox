package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "allentities_table")
public class AllEntities {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private long id;
  
  @NonNull
  @ColumnInfo(name = "name")
  private String name;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public AllEntities(long id, @NonNull String name, @NonNull String entityType) {
    this.id = id;
    this.name = name;
    this.entityType = entityType;
  }
  
  public long getId() {
    return id;
  }
  
  @NonNull public String getName() {
    return name;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}