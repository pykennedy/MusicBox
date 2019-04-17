package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "allentities_table")
public class AllEntities {
  @ColumnInfo(name = "id")
  private int id;
  
  @NonNull
  @ColumnInfo(name = "name")
  private String name;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public AllEntities(int id, @NonNull String name, @NonNull String entityType) {
    this.id = id;
    this.name = name;
    this.entityType = entityType;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull public String getName() {
    return name;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
}