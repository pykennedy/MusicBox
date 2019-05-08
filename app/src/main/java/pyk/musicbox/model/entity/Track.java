package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ALBUM;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ARTIST;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_DURATION;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ID;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_NAME;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_TABLE;

@Entity(tableName = TRACK_TABLE
        , indices = {@Index(value = {TRACK_ID}, unique = true)})
public class Track {
  @PrimaryKey
  @ColumnInfo(name = TRACK_ID)
  private long id;
  
  @NonNull
  @ColumnInfo(name = TRACK_NAME)
  private String name;
  
  @NonNull
  @ColumnInfo(name = TRACK_ARTIST)
  private String artist;
  
  @NonNull
  @ColumnInfo(name = TRACK_ALBUM)
  private String album;
  
  @NonNull
  @ColumnInfo(name = TRACK_DURATION)
  private String duration;
  
  public Track(long id, @NonNull String name, String artist, String album, String duration) {
    this.id = id;
    this.name = name;
    this.artist = (artist == null) ? "Artist #" + id : artist;
    this.album = (album == null) ? "Album #" + id : album;
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
