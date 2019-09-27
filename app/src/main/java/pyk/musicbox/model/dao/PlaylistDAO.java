package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Playlist;

@Dao
public interface PlaylistDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Playlist playlist);
  
  @Query("SELECT * FROM playlist_table WHERE id = :id")
  Playlist getPlaylistByID(long id);
  
  @Query("SELECT * FROM playlist_table")
  LiveData<List<Playlist>> getAllPlaylists();
}
