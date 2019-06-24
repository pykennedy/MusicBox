package pyk.musicbox.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.AnyEntityDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.GroupDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Track;

public class MBRepo {
  private TrackDAO               trackDAO;
  private AlbumDAO               albumDAO;
  private ArtistDAO              artistDAO;
  private Album_TrackDAO         album_trackDAO;
  private Artist_AlbumTrackDAO   artist_albumTrackDAO;
  private AnyEntityDAO           anyEntityDAO;
  private GroupDAO               groupDAO;
  private LiveData<List<Track>>  tracks;
  private LiveData<List<Album>>  albums;
  private LiveData<List<Artist>> artists;
  
  public MBRepo(Application application) {
    MBDB db = MBDB.getDB(application);
    trackDAO = db.trackDAO();
    albumDAO = db.albumDAO();
    artistDAO = db.artistDAO();
    album_trackDAO = db.album_trackDAO();
    artist_albumTrackDAO = db.artist_albumTrackDAO();
    anyEntityDAO = db.anyEntityDAO();
    groupDAO = db.groupDAO();
    
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
  
  public LiveData<List<AnyEntity>> getAllEntities(List<String> entityTypes) {
    return anyEntityDAO.getAllEntities(entityTypes);
  }
  
  public void insert(Track track) {
    new insertTrack(trackDAO, albumDAO, artistDAO, album_trackDAO, artist_albumTrackDAO,
                    anyEntityDAO).execute(track);
  }
  
  public void insert(Group group, Callback.InsertGroupCB callback) {
    new insertGroup(groupDAO, anyEntityDAO, callback).execute(group);
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
    private AnyEntityDAO         anyEntityDAO;
    
    insertTrack(TrackDAO trackDAO, AlbumDAO albumDAO, ArtistDAO artistDAO,
                Album_TrackDAO album_trackDAO, Artist_AlbumTrackDAO artist_albumTrackDAO,
                AnyEntityDAO anyEntityDAO) {
      this.trackDAO = trackDAO;
      this.albumDAO = albumDAO;
      this.artistDAO = artistDAO;
      this.album_trackDAO = album_trackDAO;
      this.artist_albumTrackDAO = artist_albumTrackDAO;
      this.anyEntityDAO = anyEntityDAO;
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
      anyEntityDAO.insert(new AnyEntity(trackID, track.getName(), "track"));
      anyEntityDAO.insert(new AnyEntity(albumID, track.getAlbum(), "album"));
      anyEntityDAO.insert(new AnyEntity(artistID, track.getArtist(), "artist"));
      
      return null;
    }
  }
  
  private static class insertGroup extends AsyncTask<Group, Void, Long> {
    GroupDAO               groupDAO;
    AnyEntityDAO           anyEntityDAO;
    Callback.InsertGroupCB callback;
    
    insertGroup(GroupDAO groupDAO, AnyEntityDAO anyEntityDAO, Callback.InsertGroupCB callback) {
      this.groupDAO = groupDAO;
      this.anyEntityDAO = anyEntityDAO;
      this.callback = callback;
    }
    
    @Override
    protected Long doInBackground(final Group... params) {
      Group group = params[0];
      long  id    = groupDAO.insert(group);
      
      if (id > 0) {
        anyEntityDAO.insert(new AnyEntity(id, group.getName(), "group"));
      }
      
      Log.e("group id", id + "");
      
      return id;
    }
    
    @Override
    protected void onPostExecute(Long id) {
      if(id > 0) {
        callback.onResponse(true, id +"");
      } else {
        callback.onResponse(false, "Failed to create Group: Duplicate Name");
      }
    }
  }
}
