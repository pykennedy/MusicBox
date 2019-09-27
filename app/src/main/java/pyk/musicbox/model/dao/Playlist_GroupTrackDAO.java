package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.entity.SortedEntity;

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
  
  @Query("DELETE FROM playlist_grouptrack_table " +
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
  
  @Query("SELECT pgtt.sortOrder AS sortOrder " +
         ", pgtt.entityID AS entityID " +
         ", tt.name AS entityName " +
         ", pgtt.entityType AS entityType " +
         ", tt.album AS otherInfo1 " +
         ", tt.artist AS otherInfo2 " +
         ", null AS otherInfo3 " +
         "FROM playlist_grouptrack_table AS pgtt " +
         "INNER JOIN track_table AS tt " +
         "ON pgtt.entityID = tt.id " +
         "AND pgtt.entityType = 'track' " +
         "AND pgtt.playlistID = :id "  +
         "UNION ALL " +
         "SELECT pgtt.sortOrder AS sortOrder " +
         ", pgtt.entityID AS entityID " +
         ", gt.name AS entityName " +
         ", pgtt.entityType AS entityType " +
         ", null AS otherInfo1 " +
         ", null AS otherInfo2 " +
         ", null AS otherInfo3 " +
         "FROM playlist_grouptrack_table AS pgtt " +
         "INNER JOIN group_table AS gt " +
         "ON pgtt.entityID = gt.id " +
         "AND pgtt.entityType = 'group' " +
         "AND pgtt.playlistID = :id "+
         "ORDER BY pgtt.sortOrder")
  LiveData<List<SortedEntity>> getItemsInPlaylist(long id);
}
