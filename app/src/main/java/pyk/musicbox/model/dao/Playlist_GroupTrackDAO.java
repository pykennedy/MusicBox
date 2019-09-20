package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Playlist_GroupTrack;

@Dao
public interface Playlist_GroupTrackDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Playlist_GroupTrack playlistGroupTrack);
  
  @Query("UPDATE playlist_grouptrack_table " +
         "SET sortOrder = :newSortOrder " +
         "WHERE playlistID = :playlistID " +
         "AND sortOrder = :oldSortOrder")
  void updateSortOrder(long playlistID, int oldSortOrder, int newSortOrder);
  
  @Query("UPDATE playlist_grouptrack_table " +
         "SET sortOrder = :sortOrder - 1 " +
         "WHERE sortOrder > :sortOrder " +
         "AND playlistID = :playlistID")
  void cascadeSortOrder(long playlistID, int sortOrder);
  
  @Query("SELECT MAX(sortOrder) " +
         "FROM playlist_grouptrack_table " +
         "WHERE playlistID = :playlistID")
  int maxSortOrder(long playlistID);
  
  @Query("SELECT * FROM playlist_grouptrack_table " +
         "WHERE playlistID = :playlistID " +
         "AND entityID = :entityID " +
         "AND entityType = :entityType")
  void delete(long playlistID, long entityID, String entityType);
  
  @Query("SELECT * FROM playlist_grouptrack_table " +
         "WHERE playlistID = :playlistID " +
         "AND entityID = :entityID " +
         "AND entityType = :entityType")
  Playlist_GroupTrack getPlaylistGroupTrackByIDs(long playlistID, long entityID, String entityType);
  
  @Query("SELECT * FROM playlist_grouptrack_table")
  LiveData<List<Playlist_GroupTrack>> getAllPlaylistGroupTracks();
  
  @Query("DELETE FROM playlist_grouptrack_table")
  void deleteAll();
}
