package pyk.musicbox.model.entity;

public class SortedEntity {
  private int sortOrder;
  
  private int    entityID;
  private String entityType;
  
  private String entityName;
  private String otherInfo1;
  private String otherInfo2;
  private String otherInfo3;
  
  public int getSortOrder() {
    return sortOrder;
  }
  
  public int getEntityID() {
    return entityID;
  }
  
  public String getEntityType() {
    return entityType;
  }
  
  public String getEntityName() {
    return entityName;
  }
  
  public String getOtherInfo1() {
    return otherInfo1;
  }
  
  public String getOtherInfo2() {
    return otherInfo2;
  }
  
  public String getOtherInfo3() {
    return otherInfo3;
  }
  
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
  
  public void setEntityID(int entityID) {
    this.entityID = entityID;
  }
  
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }
  
  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }
  
  public void setOtherInfo1(String otherInfo1) {
    this.otherInfo1 = otherInfo1;
  }
  
  public void setOtherInfo2(String otherInfo2) {
    this.otherInfo2 = otherInfo2;
  }
  
  public void setOtherInfo3(String otherInfo3) {
    this.otherInfo3 = otherInfo3;
  }
}
