package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Track;

@Dao
public interface Group_TrackDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Group_Track groupTrack);
  
  @Query("UPDATE group_track_table " +
         "SET sortorder = :newSortOrder " +
         "WHERE groupID = :groupID " +
         "AND sortorder = :oldSortOrder")
  void updateSortOrder(long groupID, int oldSortOrder, int newSortOrder);
  
  @Query("UPDATE group_track_table " +
         "SET sortorder = :sortorder - 1 " +
         "WHERE sortorder > :sortorder " +
         "AND groupID = :groupID")
  void cascadeSortOrder(long groupID, int sortorder);
  
  @Query("SELECT MAX(sortorder) " +
         "FROM group_track_table " +
         "WHERE groupID = :groupID")
  int maxSortOrder(long groupID);
  
  @Query("DELETE FROM group_track_table " +
         "WHERE groupID = :groupID " +
         "AND trackID = :trackID")
  void delete(long groupID, long trackID);
  
  @Query("SELECT * FROM group_track_table " +
         "WHERE groupID = :groupID " +
         "AND trackID = :trackID")
  Group_Track getGroupTrackByIDs(long groupID, long trackID);
  
  @Query("SELECT tt.* " +
         "FROM group_track_table AS gtt " +
         "INNER JOIN track_table AS tt " +
         "ON gtt.trackID = tt.id " +
         "AND gtt.groupID = :groupID " +
         "AND gtt.sortOrder = 1")
  Track getFirstTrack(long groupID);
  
  @Query("SELECT * FROM group_track_table")
  LiveData<List<Group_Track>> getAllGroupTracks();
  
  @Query("DELETE FROM group_track_table")
  void deleteAll();
}
