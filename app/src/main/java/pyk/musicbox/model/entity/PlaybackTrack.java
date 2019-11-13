package pyk.musicbox.model.entity;

public class PlaybackTrack {
  private long   groupID;
  private String groupName;
  private Track  track;
  
  public PlaybackTrack(long groupID, String groupName, Track track) {
    this.groupID = groupID;
    this.groupName = groupName;
    this.track = track;
  }
  
  public long getGroupID() {
    return groupID;
  }
  
  public String getGroupName() {
    return groupName;
  }
  
  public Track getTrack() {
    return track;
  }
}
