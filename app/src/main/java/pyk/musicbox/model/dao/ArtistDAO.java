package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Track;

@Dao
public interface ArtistDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Artist Artist);
  
  @Query("DELETE FROM track_table")
  void deleteAll();
  
  @Query("SELECT * FROM track_table ORDER BY name ASC")
  LiveData<List<Track>> getAllTracks();
  
  @Query("SELECT * FROM track_table WHERE id = :id")
  Track getTrackByID(long id);
}
