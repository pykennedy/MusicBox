package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "track_table")
public class Track {
  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "trackID")
  private int trackID;
  
  @NonNull
  @ColumnInfo(name = "trackname")
  private String trackName;
  
  public Track(@NonNull int trackID, @NonNull String trackName) {
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
