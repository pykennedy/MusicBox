package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Album;

import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_TABLE;

@Dao
public interface AlbumDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(Album album);
  
  @Query("DELETE FROM " + ALBUM_TABLE)
  void deleteAll();
  
  @Query("SELECT * FROM " + ALBUM_TABLE + " ORDER BY ALBUM_NAME ASC")
  LiveData<List<Album>> getAllAlbums();
  
  @Query("SELECT * FROM " + ALBUM_TABLE + " WHERE id = :id")
  Album getAlbumByID(int id);
}
