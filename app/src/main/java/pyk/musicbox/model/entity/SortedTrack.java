package pyk.musicbox.model.entity;

import android.arch.persistence.room.Embedded;

public class SortedTrack {
  private int sortOrder;
  @Embedded
  private Track track;
  
  public int getSortOrder() {
    return sortOrder;
  }
  
  public Track getTrack() {
    return track;
  }
  
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
  
  public void setTrack(Track track) {
    this.track = track;
  }
}
