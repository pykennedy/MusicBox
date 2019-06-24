package pyk.musicbox.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import pyk.musicbox.model.entity.Group;

@Dao
public interface GroupDAO {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Group group);
  
  @Query("DELETE FROM group_table")
  void deleteAll();
  
  @Query("SELECT * FROM group_table WHERE id = :id")
  Group getGroupByID(long id);
}
