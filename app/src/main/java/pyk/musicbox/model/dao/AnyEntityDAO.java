package pyk.musicbox.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import pyk.musicbox.model.entity.AnyEntity;

@Dao
public interface AnyEntityDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(AnyEntity entity);
  
  @Query("DELETE FROM anyentity_table")
  void deleteAll();
  
  @Query("SELECT * FROM anyentity_table WHERE entityType IN (:entityTypes) ORDER BY name ASC")
  LiveData<List<AnyEntity>> getAllEntities(List<String> entityTypes);
  
  
}
