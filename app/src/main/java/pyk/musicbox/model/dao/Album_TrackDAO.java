package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.Track;

@Dao
public interface Album_TrackDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Album_Track album_track);
  
  @Query("DELETE FROM album_track_table")
  void deleteAll();
  
  @Query("SELECT tt.* " +
         "FROM album_track_table AS att " +
         "INNER JOIN track_table AS tt " +
         "ON att.trackID = tt.id " +
         "AND att.albumID = :id")
  LiveData<List<Track>> getTracksByAlbumID(long id);
}
