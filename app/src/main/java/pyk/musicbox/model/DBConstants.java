package pyk.musicbox.model;

public interface DBConstants {
  interface BaseConstants {
    String ID = "id";
  }
  
  interface AlbumConstants {
    String ALBUM_TABLE = "album_table";
    String ALBUM_NAME  = "album_name";
    String ALBUM_ARTIST = "album_artist";
    String ALBUM_KEY   = "album_key";
  }
  
  interface Album_TrackConstants {
    String ALBUM_TRACK_TABLE   = "album_track_table";
    String ALBUM_TRACK_ALBUMID = "albumID";
    String ALBUM_TRACK_TRACKID = "trackID";
  }
  
  interface AllEntitiesConstants {
    String ALLENTITIES_TABLE      = "allentities_table";
    String ALLENTITIES_ID         = "id";
    String ALLENTITIES_NAME       = "name";
    String ALLENTITIES_ENTITYTYPE = "entityType";
  }
  
  interface ArtistConstants {
    String ARTIST_TABLE = "artist_table";
    String ARTIST_ID    = "id";
    String ARTIST_NAME  = "name";
  }
  
  interface Artist_AlbumTrackConstants {
    String ARTIST_ALBUMTRACK_TABLE      = "artist_albumtrack_table";
    String ARTIST_ALBUMTRACK_ARTISTID   = "artistID";
    String ARTIST_ALBUMTRACK_ENTITYID   = "entityID";
    String ARTIST_ALBUMTRACK_ENTITYTYPE = "entityType";
  }
  
  interface GroupConstants {
    String GROUP_TABLE = "group_table";
    String GROUP_ID    = "id";
    String GROUP_NAME  = "name";
  }
  
  interface Group_TrackConstants {
    String GROUP_TRACK_TABLE   = "group_track_table";
    String GROUP_TRACK_GROUPID = "groupID";
    String GROUP_TRACK_TRACKID = "trackID";
  }
  
  interface PlaylistConstants {
    String PLAYLIST_TABLE = "playlist_table";
    String PLAYLIST_ID    = "id";
    String PLAYLIST_NAME  = "name";
  }
  
  interface Playlist_GroupTrackConstants {
    String PLAYLIST_GROUPTRACK_TABLE      = "playlist_grouptrack_table";
    String PLAYLIST_GROUPTRACK_PLAYLISTID = "playlistID";
    String PLAYLIST_GROUPTRACK_ENTITYID   = "entityID";
    String PLAYLIST_GROUPTRACK_ENTITYTYPE = "entityType";
  }
  
  interface TrackConstants {
    String TRACK_TABLE    = "track_table";
    String TRACK_ID       = "id";
    String TRACK_KEY      = "key";
    String TRACK_NAME     = "name";
    String TRACK_ARTIST   = "artist";
    String TRACK_ALBUM    = "album";
    String TRACK_DURATION = "duration";
  }
}
