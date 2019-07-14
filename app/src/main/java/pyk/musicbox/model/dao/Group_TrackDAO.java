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
  
  @Query("DELETE FROM group_track_table")
  void deleteAll();
}
