package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ALBUM;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ARTIST;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_DURATION;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ID;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_NAME;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_TABLE;

//TODO: all entities autogenerate their key, but their ID is extended from a parent id
//TODO:   that will also be autogenerated. This way i can guarantee unique ID's across
//TODO:   for all objects inside the AllEntities class.

@Entity(tableName = TRACK_TABLE)
public class Track {
  @PrimaryKey
  @ColumnInfo(name = TRACK_ID)
  private int id;
  
  @NonNull
  @ColumnInfo(name = TRACK_NAME)
  private String name;
  
  @ColumnInfo(name = TRACK_ARTIST)
  private String artist;
  
  @ColumnInfo(name = TRACK_ALBUM)
  private String album;
  
  @ColumnInfo(name = TRACK_DURATION)
  private String duration;
  
  public Track(int id, @NonNull String name, String artist, String album, String duration) {
    this.id = id;
    this.name = name;
    this.artist = artist;
    this.album = album;
    this.duration = duration;
  }
  
  public int getId() {
    return id;
  }
  
  @NonNull
  public String getName() {
    return name;
  }
  
  public String getAlbum() {
    return album;
  }
  
  public String getArtist() {
    return artist;
  }
  
  public String getDuration() {
    return duration;
  }
}
