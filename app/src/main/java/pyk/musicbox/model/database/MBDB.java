package pyk.musicbox.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.entity.Track;

@Database(entities = {Track.class}, version = 1)
public abstract class MBDB extends RoomDatabase {
  private static volatile MBDB instance;
  
  public abstract TrackDAO trackDAO();
  
  public static MBDB getDB(final Context context) {
    if (instance == null) {
      synchronized (MBDB.class) {
        if (instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(), MBDB.class, "mbdb")
                         .build();
        }
      }
    }
    return instance;
  }
}
