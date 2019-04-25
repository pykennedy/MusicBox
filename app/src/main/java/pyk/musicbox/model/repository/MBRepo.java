package pyk.musicbox.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Track;

public class MBRepo {
  private TrackDAO              trackDAO;
  private AlbumDAO albumDAO;
  private LiveData<List<Track>> tracks;
  private LiveData<List<Album>> albums;
  
  public MBRepo(Application application) {
    MBDB db = MBDB.getDB(application);
    trackDAO = db.trackDAO();
    tracks = trackDAO.getAllTracks();
    albumDAO = db.albumDAO();
    albums = albumDAO.getAllAlbums();
  }
  
  public LiveData<List<Track>> getAllTracks() {
    return tracks;
  }
  
  public LiveData<List<Album>> getAllAlbums() {
    return albums;
  }
  
  public void insert(Track track) {
    new insertTrack(trackDAO).execute(track);
  }
  
  /***********************************************************************************************
   * Async Tasks *********************************************************************************
   ***********************************************************************************************/
  
  private static class insertTrack extends AsyncTask<Track, Void, Void> {
    private TrackDAO asyncTrackDao;
    
    insertTrack(TrackDAO dao) {
      this.asyncTrackDao = dao;
    }
    
    @Override
    protected Void doInBackground(final Track... params) {
      asyncTrackDao.insert(params[0]);
      return null;
    }
  }
}
