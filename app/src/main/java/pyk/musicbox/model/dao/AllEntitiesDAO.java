package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.AllEntities;

@Dao
public interface AllEntitiesDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(AllEntities entity);
  
  @Query("DELETE FROM allentities_table")
  void deleteAll();
  
  @Query("SELECT * FROM allentities_table WHERE entityType IN (:entityTypes) ORDER BY name ASC")
  LiveData<List<AllEntities>> getAllEntities(List<String> entityTypes);
  
  
}
