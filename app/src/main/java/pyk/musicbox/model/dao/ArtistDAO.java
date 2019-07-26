package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Artist;

@Dao
public interface ArtistDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Artist Artist);
  
  @Query("DELETE FROM artist_table")
  void deleteAll();
  
  @Query("SELECT * FROM artist_table ORDER BY name ASC")
  LiveData<List<Artist>> getAllArtists();
  
  @Query("SELECT * FROM artist_table WHERE id = :id")
  Artist getArtistByID(long id);
  
  @Query("SELECT * FROM artist_table WHERE name = :name")
  Artist getArtistByName(String name);
  
  @Query("SELECT id FROM artist_table WHERE name = :name")
  Long getArtistIDByName(String name);
}
