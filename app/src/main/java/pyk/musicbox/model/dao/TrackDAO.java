package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.PlaybackEntity;
import pyk.musicbox.model.entity.PlaybackGrouping;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.model.entity.Track;

@Dao
public interface TrackDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Track track);
  
  @Query("DELETE FROM track_table")
  void deleteAll();
  
  @Query("SELECT * FROM track_table ORDER BY name ASC")
  LiveData<List<Track>> getAllTracks();
  
  @Query("SELECT * FROM track_table WHERE id = :id")
  Track getTrackByID(long id);
  
  @Query("SELECT gtt.sortOrder, tt.* " +
         "FROM group_track_table AS gtt " +
         "INNER JOIN track_table AS tt " +
         "ON gtt.trackID = tt.id " +
         "AND gtt.groupID = :id " +
         "ORDER BY gtt.sortOrder ASC")
  LiveData<List<SortedTrack>> getTracksInGroup(long id);
  
  @Query("SELECT 0 as sortOrder, tt.* " +
         "FROM album_track_table AS att " +
         "INNER JOIN track_table AS tt " +
         "ON att.trackID = tt.id " +
         "AND att.albumID = :id")
  LiveData<List<SortedTrack>> getTracksInAlbum(long id);
  
  @Query("SELECT 0 AS sortOrder " +
         ", tt.id AS entityID " +
         ", 'track' AS entityType " +
         ", tt.name AS entityName " +
         "FROM track_table AS tt " +
         "LEFT JOIN group_track_table AS gtt " +
         "ON tt.id = gtt.trackID " +
         "WHERE gtt.trackID IS NULL " +
         "UNION ALL " +
         "SELECT DISTINCT 0 AS sortOrder " +
         ", gtt.groupID AS entityID " +
         ", 'group' AS entityType " +
         ", gt.name AS entityName " +
         "FROM track_table AS tt " +
         "INNER JOIN group_track_table AS gtt " +
         "ON tt.id = gtt.trackID " +
         "INNER JOIN group_table AS gt " +
         "ON gtt.groupID = gt.id " +
         "ORDER BY entityName")
  LiveData<List<PlaybackEntity>> getAllPlaybackEntities();
  
  @Query("SELECT pgtt.sortOrder AS sortOrder " +
         ", tt.id AS entityID " +
         ", 'track' AS entityType " +
         ", tt.name AS entityName " +
         "FROM track_table AS tt " +
         "INNER JOIN playlist_grouptrack_table AS pgtt " +
         "ON tt.id = pgtt.entityID " +
         "AND pgtt.entityType = 'track' " +
         "AND pgtt.playlistID = :playlistID " +
         "UNION ALL " +
         "SELECT DISTINCT pgtt.sortOrder AS sortOrder " +
         ", gtt.groupID AS entityID " +
         ", 'group' AS entityType " +
         ", gt.name AS entityName " +
         "FROM track_table AS tt " +
         "INNER JOIN group_track_table AS gtt " +
         "ON tt.id = gtt.trackID " +
         "INNER JOIN group_table AS gt " +
         "ON gtt.groupID = gt.id " +
         "INNER JOIN playlist_grouptrack_table AS pgtt " +
         "ON gtt.groupID = pgtt.entityID " +
         "AND pgtt.entityType = 'group' " +
         "AND pgtt.playlistID = :playlistID " +
         "ORDER BY sortOrder")
  LiveData<List<PlaybackEntity>> getPlaybackEntitiesInPlaylist(long playlistID);
  
  @Query("SELECT gtt.sortOrder AS sortOrder" +
         ", gt.id AS groupID " +
         ", gt.name AS groupName " +
         ", tt.* " +
         "FROM group_track_table AS gtt " +
         "INNER JOIN track_table AS tt " +
         "ON gtt.trackID = tt.id " +
         "AND gtt.groupID IN (:groups)" +
         "INNER JOIN group_table AS gt " +
         "ON gtt.groupID = gt.id")
  LiveData<List<PlaybackGrouping>> getAllPlaybackGroupings(List<Long> groups);
  
  @Query("SELECT * " +
         "FROM track_table AS tt " +
         "WHERE tt.id IN (:trackIDs)")
  LiveData<List<Track>> getTracks(List<Long> trackIDs);
}
