package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ColumnInfo.NOCASE;

@Entity(tableName = "anyentity_table"
    , indices = {@Index(value = {"entityID", "entityType"}, unique = true),
                 @Index("name")})
public class AnyEntity {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private long id;
  
  @ColumnInfo(name = "entityID")
  private long entityID;
  
  @NonNull
  @ColumnInfo(name = "name", collate = NOCASE)
  private String name;
  
  @NonNull
  @ColumnInfo(name = "entityType")
  private String entityType;
  
  public AnyEntity(long entityID, @NonNull String name, @NonNull String entityType) {
    this.entityID = entityID;
    this.name = name;
    this.entityType = entityType;
  }
  
  public long getId() {
    return id;
  }
  
  public long getEntityID() {
    return entityID;
  }
  
  @NonNull public String getName() {
    return name;
  }
  
  @NonNull public String getEntityType() {
    return entityType;
  }
  
  public void setId(long id) {
    this.id = id;
  }
}