package pyk.musicbox.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Track;

import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_ARTIST;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_KEY;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_NAME;
import static pyk.musicbox.model.DBConstants.AlbumConstants.ALBUM_TABLE;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ALBUM;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_ARTIST;
import static pyk.musicbox.model.DBConstants.TrackConstants.TRACK_TABLE;

@Database(entities = {Track.class, Album.class}, version = 1)
public abstract class MBDB extends RoomDatabase {
  private static volatile MBDB instance;
  
  public abstract TrackDAO trackDAO();
  public abstract AlbumDAO albumDAO();
  
  public static MBDB getDB(final Context context) {
    if (instance == null) {
      synchronized (MBDB.class) {
        if (instance == null) {
          context.deleteDatabase("mbdb");
          instance = Room.databaseBuilder(context.getApplicationContext(), MBDB.class, "mbdb")
                         //.addCallback(purgeDB)
                         .addCallback(createTriggers)
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
  
  private static RoomDatabase.Callback createTriggers = new RoomDatabase.Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      /*
      CREATE TRIGGER IF NOT EXISTS [name] AFTER INSERT
      ON TRACK_TABLE
      BEGIN
        INSERT INTO ALBUM_TABLE(ALBUM_NAME, ALBUM_ARTIST, ALBUM_KEY)
        VALUES(new.TRACK_ALBUM, new.TRACK_ARTIST, (new.TRACK_ALBUM || new.TRACK_ARTIST))
      END
       */
      db.execSQL(
          "CREATE TRIGGER IF NOT EXISTS InsertTrackThenInsertAlbum AFTER INSERT " +
          "ON " + TRACK_TABLE + " FOR EACH ROW " +
          "BEGIN " +
          "INSERT INTO " + ALBUM_TABLE + " (" + ALBUM_NAME + ", " + ALBUM_ARTIST + ", " +
          ALBUM_KEY + ") " +
          "VALUES (" + "new." + TRACK_ALBUM + ", new." + TRACK_ARTIST + ", (new." + TRACK_ALBUM +
          " || " + "new." + TRACK_ARTIST + ")); " +
          "END;");
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
