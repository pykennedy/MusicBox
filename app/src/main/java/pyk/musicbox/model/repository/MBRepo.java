package pyk.musicbox.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Track;

public class MBRepo {
  private TrackDAO              trackDAO;
  private AlbumDAO              albumDAO;
  private ArtistDAO             artistDAO;
  private Album_TrackDAO        album_trackDAO;
  private Artist_AlbumTrackDAO  artist_albumTrackDAO;
  private LiveData<List<Track>> tracks;
  private LiveData<List<Album>> albums;
  
  public MBRepo(Application application) {
    MBDB db = MBDB.getDB(application);
    trackDAO = db.trackDAO();
    albumDAO = db.albumDAO();
    artistDAO = db.artistDAO();
    album_trackDAO = db.album_trackDAO();
    artist_albumTrackDAO = db.artist_albumTrackDAO();
    
    tracks = trackDAO.getAllTracks();
    albums = albumDAO.getAllAlbums();
  }
  
  public LiveData<List<Track>> getAllTracks() {
    return tracks;
  }
  
  public LiveData<List<Album>> getAllAlbums() {
    return albums;
  }
  
  public void insert(Track track) {
    new insertTrack(trackDAO, albumDAO, artistDAO, album_trackDAO, artist_albumTrackDAO).execute(
        track);
  }
  
  /***********************************************************************************************
   * Async Tasks *********************************************************************************
   ***********************************************************************************************/
  
  private static class insertTrack extends AsyncTask<Track, Void, Void> {
    private TrackDAO             trackDAO;
    private AlbumDAO             albumDAO;
    private ArtistDAO            artistDAO;
    private Album_TrackDAO       album_trackDAO;
    private Artist_AlbumTrackDAO artist_albumTrackDAO;
    
    insertTrack(TrackDAO trackDAO, AlbumDAO albumDAO, ArtistDAO artistDAO,
                Album_TrackDAO album_trackDAO, Artist_AlbumTrackDAO artist_albumTrackDAO) {
      this.trackDAO = trackDAO;
      this.albumDAO = albumDAO;
      this.artistDAO = artistDAO;
      this.album_trackDAO = album_trackDAO;
      this.artist_albumTrackDAO = artist_albumTrackDAO;
    }
    
    @Override
    protected Void doInBackground(final Track... params) {
      Track track = params[0];
      Album album = new Album(track.getAlbum(), track.getArtist(),
                              track.getAlbum() + track.getArtist());
      Artist artist = new Artist(track.getArtist());
      
      long trackID  = trackDAO.insert(track);
      long albumID  = albumDAO.insert(album);
      long artistID = artistDAO.insert(artist);
      
      album_trackDAO.insert(new Album_Track(albumID, trackID));
      artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, albumID, "album"));
      artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, trackID, "track"));
      
      return null;
    }
  }
}
