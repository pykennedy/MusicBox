package pyk.musicbox.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.AllEntitiesDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.AllEntities;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Track;

public class MBRepo {
  private TrackDAO                    trackDAO;
  private AlbumDAO                    albumDAO;
  private ArtistDAO                   artistDAO;
  private Album_TrackDAO              album_trackDAO;
  private Artist_AlbumTrackDAO        artist_albumTrackDAO;
  private AllEntitiesDAO              allEntitiesDAO;
  private LiveData<List<Track>>       tracks;
  private LiveData<List<Album>>       albums;
  private LiveData<List<Artist>>      artists;
  
  public MBRepo(Application application) {
    MBDB db = MBDB.getDB(application);
    trackDAO = db.trackDAO();
    albumDAO = db.albumDAO();
    artistDAO = db.artistDAO();
    album_trackDAO = db.album_trackDAO();
    artist_albumTrackDAO = db.artist_albumTrackDAO();
    allEntitiesDAO = db.allEntitiesDAO();
    
    tracks = trackDAO.getAllTracks();
    albums = albumDAO.getAllAlbums();
    artists = artistDAO.getAllArtists();
  }
  
  public LiveData<List<Track>> getAllTracks() {
    return tracks;
  }
  
  public LiveData<List<Album>> getAllAlbums() {
    return albums;
  }
  
  public LiveData<List<Artist>> getAllArtists() { return artists; }
  
  public LiveData<List<AllEntities>> getAllEntities(List<String> entityTypes) {
    return allEntitiesDAO.getAllEntities(entityTypes);
  }
  
  public void insert(Track track) {
    new insertTrack(trackDAO, albumDAO, artistDAO, album_trackDAO, artist_albumTrackDAO,
                    allEntitiesDAO).execute(
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
    private AllEntitiesDAO       allEntitiesDAO;
    
    insertTrack(TrackDAO trackDAO, AlbumDAO albumDAO, ArtistDAO artistDAO,
                Album_TrackDAO album_trackDAO, Artist_AlbumTrackDAO artist_albumTrackDAO,
                AllEntitiesDAO allEntitiesDAO) {
      this.trackDAO = trackDAO;
      this.albumDAO = albumDAO;
      this.artistDAO = artistDAO;
      this.album_trackDAO = album_trackDAO;
      this.artist_albumTrackDAO = artist_albumTrackDAO;
      this.allEntitiesDAO = allEntitiesDAO;
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
      allEntitiesDAO.insert(new AllEntities(trackID, track.getName(), "track"));
      allEntitiesDAO.insert(new AllEntities(albumID, track.getAlbum(), "album"));
      allEntitiesDAO.insert(new AllEntities(artistID, track.getArtist(), "artist"));
      
      return null;
    }
  }
}
