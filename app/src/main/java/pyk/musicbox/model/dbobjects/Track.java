package pyk.musicbox.model.dbobjects;

public class Track {
  private int trackID;
  private String trackName;
  
  public Track(int trackID, String trackName) {
    this.trackID = trackID;
    this.trackName = trackName;
  }
  
  public int getTrackID() {
    return trackID;
  }
  
  public String getTrackName() {
    return trackName;
  }
}
