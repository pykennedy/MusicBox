package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Track;

@Dao
public interface TrackDAO {
  @Insert
  void insert(Track track);
  
  @Query("DELETE FROM track_table")
  void deleteAll();
  
  @Query("SELECT * FROM track_table ORDER BY trackname ASC")
  LiveData<List<Track>> getAllTracks();
}
