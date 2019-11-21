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

  @ColumnInfo(name = "otherInfo1")
  private String otherInfo1;

  @ColumnInfo(name = "otherInfo2")
  private String otherInfo2;
  
  @NonNull
  @ColumnInfo(name = "searchtext")
  private String searchText;
  
  public AnyEntity(long entityID, @NonNull String name, @NonNull String entityType, String otherInfo1, String otherInfo2, @NonNull String searchText) {
    this.entityID = entityID;
    this.name = name;
    this.otherInfo1 = otherInfo1;
    this.otherInfo2 = otherInfo2;
    this.entityType = entityType;
    this.searchText = searchText;
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
  
  @NonNull public String getSearchText() {
    return searchText;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getOtherInfo1() {
    return otherInfo1;
  }
  
  public String getOtherInfo2() {
    return otherInfo2;
  }
}