package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.AllEntitiesConstants.ALLENTITIES_ENTITYTYPE;
import static pyk.musicbox.model.DBConstants.AllEntitiesConstants.ALLENTITIES_ID;
import static pyk.musicbox.model.DBConstants.AllEntitiesConstants.ALLENTITIES_NAME;
import static pyk.musicbox.model.DBConstants.AllEntitiesConstants.ALLENTITIES_TABLE;

@Entity(tableName = ALLENTITIES_TABLE)
public class AllEntities {
  @ColumnInfo(name = ALLENTITIES_ID)
  private int id;
  
  @NonNull
  @ColumnInfo(name = ALLENTITIES_NAME)
  private String name;
  
  @NonNull
  @ColumnInfo(name = ALLENTITIES_ENTITYTYPE)
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