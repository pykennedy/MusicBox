package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ALBUM;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ARTIST;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_DURATION;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_KEY;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_NAME;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_TABLE;

@Entity(tableName = TRACK_TABLE
        , indices = {@Index(value = {TRACK_KEY}, unique = true)})
public class Track extends Base{
  @ColumnInfo(name = TRACK_KEY)
  private int key;
  
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
  
  public Track(int key, @NonNull String name, String artist, String album, String duration) {
    this.key = key;
    this.name = name;
    this.artist = (artist == null) ? "Artist #" + key : artist;
    this.album = (album == null) ? "Album #" + key : album;
    this.duration = (duration == null) ? "00:00" + key : duration;
  }
  
  @Override
  public int getId() { return super.getId(); }
  
  public int getKey() {
    return key;
  }
  
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
