package pyk.musicbox.model.entity;

import android.arch.persistence.room.Embedded;

public class SortedEntity {
  private int sortOrder;
  @Embedded
  private Playlist_GroupTrack playlistGroupTrack;
}
