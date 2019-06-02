package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Track;

@Dao
public interface Artist_AlbumTrackDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Artist_AlbumTrack artist_albumTrack);
  
  @Query("DELETE FROM track_table")
  void deleteAll();
  
  @Query("SELECT at.* " +
         "FROM artist_albumtrack_table AS aatt " +
         "INNER JOIN album_table AS at " +
         "ON aatt.entityID = at.id " +
         "AND aatt.artistID = :id " +
         "AND aatt.entityType = 'album'")
  LiveData<List<Album>> getAlbumsByArtistID(long id);
  
  @Query("SELECT tt.* " +
         "FROM artist_albumtrack_table AS aatt " +
         "INNER JOIN track_table AS tt " +
         "ON aatt.entityID = tt.id " +
         "AND aatt.artistID = :id " +
         "AND aatt.entityType = 'track'")
  LiveData<List<Track>> getTracksByArtistID(long id);
}
