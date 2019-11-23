package pyk.musicbox.model.entity;

public class PlaybackEntity {
  private int sortOrder;
  private long    entityID;
  private String entityType;
  
  public int getSortOrder() {
    return sortOrder;
  }
  
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
  
  public long getEntityID() {
    return entityID;
  }
  
  public void setEntityID(long entityID) {
    this.entityID = entityID;
  }
  
  public String getEntityType() {
    return entityType;
  }
  
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }
}
