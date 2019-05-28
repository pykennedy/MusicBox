package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ID;

@Entity(tableName = "track_table"
        , indices = {@Index(value = {TRACK_ID}, unique = true)})
public class Track {
  @PrimaryKey
  @ColumnInfo(name = "id")
  private long id;
  
  @NonNull
  @ColumnInfo(name = "name")
  private String name;
  
  @NonNull
  @ColumnInfo(name = "album")
  private String album;
  
  @NonNull
  @ColumnInfo(name = "artist")
  private String artist;
  
  @NonNull
  @ColumnInfo(name = "duration")
  private String duration;
  
  public Track(long id, @NonNull String name, String album, String artist, String duration) {
    this.id = id;
    this.name = name;
    this.album = (album == null) ? "Album #" + id : album;
    this.artist = (artist == null) ? "Artist #" + id : artist;
    this.duration = (duration == null) ? "00:00" + id : duration;
  }
  
  public long getId() { return id; }
  
  @NonNull
  public String getName() {
    return name;
  }
  
  @NonNull
  public String getAlbum() {
    return album;
  }
  
  @NonNull
  public String getArtist() {
    return artist;
  }
  
  @NonNull
  public String getDuration() {
    return duration;
  }
}
