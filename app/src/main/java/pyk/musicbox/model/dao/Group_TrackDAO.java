package pyk.musicbox.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import pyk.musicbox.model.entity.Group_Track;

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
         "FROM group_track_table")
  int maxSortOrder();
  
  @Query("DELETE FROM group_track_table " +
         "WHERE groupID = :groupID " +
         "AND trackID = :trackID")
  void delete(long groupID, long trackID);
  
  @Query("SELECT * FROM group_track_table " +
         "WHERE sortorder = :sortorder " +
         "AND groupID = :groupID")
  Group_Track getGroupTrackBySortOrder(long groupID, int sortorder);
  
  @Query("DELETE FROM group_track_table")
  void deleteAll();
}
