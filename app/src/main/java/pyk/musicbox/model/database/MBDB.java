package pyk.musicbox.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.AnyEntityDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.GroupDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Track;

@Database(
    entities = {Track.class, Album.class, Artist.class, Album_Track.class, Artist_AlbumTrack.class,
                AnyEntity.class, Group.class},
    version = 1)
public abstract class MBDB extends RoomDatabase {
  private static volatile MBDB instance;
  
  public abstract TrackDAO trackDAO();
  
  public abstract AlbumDAO albumDAO();
  
  public abstract ArtistDAO artistDAO();
  
  public abstract Album_TrackDAO album_trackDAO();
  
  public abstract Artist_AlbumTrackDAO artist_albumTrackDAO();
  
  public abstract AnyEntityDAO anyEntityDAO();
  
  public abstract GroupDAO groupDAO();
  
  public static MBDB getDB(final Context context) {
    if (instance == null) {
      synchronized (MBDB.class) {
        if (instance == null) {
          //context.deleteDatabase("mbdb");
          instance = Room.databaseBuilder(context.getApplicationContext(), MBDB.class, "mbdb")
                         //.addCallback(purgeDB)
                         .build();
        }
      }
    }
    return instance;
  }
  
  private static RoomDatabase.Callback purgeDB = new RoomDatabase.Callback() {
    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
      new PurgeDB(instance).execute();
    }
  };
  
  /***********************************************************************************************
   * Async Tasks *********************************************************************************
   ***********************************************************************************************/
  
  private static class PurgeDB extends AsyncTask<Void, Void, Void> {
    private final MBDB db;
    
    PurgeDB(MBDB db) {
      this.db = db;
    }
    
    @Override
    protected Void doInBackground(final Void... params) {
      db.clearAllTables();
      return null;
    }
  }
}
