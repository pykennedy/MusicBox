package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Album;

@Dao
public interface AlbumDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Album album);
  
  @Query("DELETE FROM album_table")
  void deleteAll();
  
  @Query("SELECT * FROM album_table ORDER BY name ASC")
  LiveData<List<Album>> getAllAlbums();
  
  @Query("SELECT * FROM album_table WHERE id = :id")
  Album getAlbumByID(long id);
  
  @Query("SELECT * FROM album_table WHERE `key` = :key")
  Album getAlbumByKey(String key);
}
