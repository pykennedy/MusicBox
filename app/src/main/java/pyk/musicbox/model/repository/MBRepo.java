package pyk.musicbox.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.AnyEntityDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.GroupDAO;
import pyk.musicbox.model.dao.Group_TrackDAO;
import pyk.musicbox.model.dao.PlaylistDAO;
import pyk.musicbox.model.dao.Playlist_GroupTrackDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Playlist;
import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.model.entity.Track;

public class MBRepo {
  private TrackDAO               trackDAO;
  private AlbumDAO               albumDAO;
  private ArtistDAO              artistDAO;
  private Album_TrackDAO         album_trackDAO;
  private Artist_AlbumTrackDAO   artist_albumTrackDAO;
  private AnyEntityDAO           anyEntityDAO;
  private GroupDAO               groupDAO;
  private Group_TrackDAO         groupTrackDAO;
  private PlaylistDAO            playlistDAO;
  private Playlist_GroupTrackDAO playlistGroupTrackDAO;
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
    groupTrackDAO = db.groupTrackDAO();
    playlistDAO = db.playlistDAO();
    playlistGroupTrackDAO = db.playlistGroupTrackDAO();
    
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
  
  public LiveData<List<AnyEntity>> searchAllEntities(List<String> entityTypes, String text) {
    return anyEntityDAO.searchAllEntities(entityTypes, "%" + text + "%");
  }
  
  public LiveData<List<SortedTrack>> getTracksInGroup(Long id) {
    return trackDAO.getTracksInGroup(id);
  }
  public LiveData<List<SortedTrack>> getTracksInAlbum(Long id) {
    return trackDAO.getTracksInAlbum(id);
  }
  
  public LiveData<List<SortedEntity>> getItemsInPlaylist(long id) {
    return playlistGroupTrackDAO.getItemsInPlaylist(id);
  }
  
  public LiveData<List<SortedEntity>> getItemsInArtist(long id) {
    return artist_albumTrackDAO.getItemsInArtist(id);
  }
  
  public void insert(Track track) {
    new insertTrack(trackDAO, albumDAO, artistDAO, album_trackDAO, artist_albumTrackDAO,
                    anyEntityDAO).execute(track);
  }
  
  public void insert(Group group, Callback.InsertGroupCB callback) {
    new insertGroup(groupDAO, anyEntityDAO, callback).execute(group);
  }
  
  public void insert(Group_Track groupTrack) {
    new insertGroupTrack(groupTrackDAO).execute(groupTrack);
  }
  
  public void updateGroupTrackSortOrder(long groupID, int oldSortOrder, int newSortOrder) {
    new updateGroupTrackSortOrder(groupTrackDAO, groupID, oldSortOrder, newSortOrder).execute();
  }
  
  public void deleteTrackFromGroup(long groupID, long trackID, int sortOrder) {
    new deleteTrackFromGroup(groupTrackDAO, groupID, trackID, sortOrder).execute();
  }
  
  public void insert(Playlist playlist, Callback.InsertPlaylistCB callback) {
    new insertPlaylist(playlistDAO, anyEntityDAO, callback).execute(playlist);
  }
  
  public void insert(Playlist_GroupTrack playlistGroupTrack) {
    new insertPlaylistGroupTrack(playlistGroupTrackDAO).execute(playlistGroupTrack);
  }
  
  public void updatePlaylistGroupTrackSortOrder(long playlistID, int oldSortOrder,
                                                int newSortOrder) {
    new updatePlaylistGroupTrackSortOrder(playlistGroupTrackDAO, playlistID, oldSortOrder,
                                          newSortOrder)
        .execute();
  }
  
  public void deleteGroupTrackFromPlaylist(long playlistID, long trackID, String entityType,
                                           int sortOrder) {
    new deleteGroupTrackFromPlaylist(playlistGroupTrackDAO, playlistID, trackID, entityType,
                                     sortOrder).execute();
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
      
      String trackSearchtext  =
          track.getName() + "|||" + track.getAlbum() + "|||" + track.getArtist();
      String albumSearchText  = track.getAlbum() + "|||" + track.getArtist();
      String artistSearchText = track.getArtist();
      
      long trackID = trackDAO.insert(track);
      
      if (trackID > -1) { // if new and unique track
        long albumID = albumDAO.insert(album);
        albumID = (albumID > -1) ? albumID : albumDAO.getAlbumIDByKey(album.getKey());
        long artistID = artistDAO.insert(artist);
        artistID = (artistID > -1) ? artistID : artistDAO.getArtistIDByName(artist.getName());
        
        album_trackDAO.insert(new Album_Track(albumID, trackID));
        artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, albumID, "album"));
        artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, trackID, "track"));
        anyEntityDAO.insert(new AnyEntity(trackID, track.getName(), "track", trackSearchtext));
        anyEntityDAO.insert(new AnyEntity(albumID, album.getName(), "album", albumSearchText));
        anyEntityDAO.insert(new AnyEntity(artistID, artist.getName(), "artist", artistSearchText));
      }
      
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
      
      if (id > -1) {
        anyEntityDAO.insert(new AnyEntity(id, group.getName(), "group", group.getName()));
      }
      
      return id;
    }
    
    @Override
    protected void onPostExecute(Long id) {
      if (id > 0) {
        callback.onResponse(true, id + "");
      } else {
        callback.onResponse(false, "Failed to create Group: Duplicate Name");
      }
    }
  }
  
  private static class insertGroupTrack extends AsyncTask<Group_Track, Void, Void> {
    Group_TrackDAO groupTrackDAO;
    
    insertGroupTrack(Group_TrackDAO groupTrackDAO) {
      this.groupTrackDAO = groupTrackDAO;
    }
    
    @Override
    protected Void doInBackground(final Group_Track... params) {
      Group_Track groupTrack = params[0];
      int         sortOrder  = groupTrackDAO.maxSortOrder(groupTrack.getGroupID()) + 1;
      groupTrack.setSortOrder(sortOrder);
      groupTrackDAO.insert(groupTrack);
      
      return null;
    }
  }
  
  private static class updateGroupTrackSortOrder extends AsyncTask<Void, Void, Void> {
    Group_TrackDAO groupTrackDAO;
    long           groupID;
    int            oldSortOrder;
    int            newSortOrder;
    
    updateGroupTrackSortOrder(Group_TrackDAO groupTrackDAO, long groupID, int oldSortOrder,
                              int newSortOrder) {
      this.groupTrackDAO = groupTrackDAO;
      this.groupID = groupID;
      this.oldSortOrder = oldSortOrder;
      this.newSortOrder = newSortOrder;
    }
    
    @Override
    protected Void doInBackground(Void... params) {
      // change conflicting item to a spoof sortOrder
      groupTrackDAO.updateSortOrder(groupID, newSortOrder, 999);
      // move the item
      groupTrackDAO.updateSortOrder(groupID, oldSortOrder, newSortOrder);
      // swap the conflicting item with old spot
      groupTrackDAO.updateSortOrder(groupID, 999, oldSortOrder);
      
      return null;
    }
  }
  
  private static class deleteTrackFromGroup extends AsyncTask<Void, Void, Void> {
    Group_TrackDAO groupTrackDAO;
    long           groupID;
    long           trackID;
    int            sortOrder;
    
    deleteTrackFromGroup(Group_TrackDAO groupTrackDAO, long groupID, long trackID, int sortOrder) {
      this.groupTrackDAO = groupTrackDAO;
      this.groupID = groupID;
      this.trackID = trackID;
      this.sortOrder = sortOrder;
    }
    
    @Override
    protected Void doInBackground(Void... params) {
      groupTrackDAO.delete(groupID, trackID);
      groupTrackDAO.cascadeSortOrder(groupID, sortOrder);
      
      return null;
    }
  }
  
  private static class insertPlaylist extends AsyncTask<Playlist, Void, Long> {
    PlaylistDAO               playlistDAO;
    AnyEntityDAO              anyEntityDAO;
    Callback.InsertPlaylistCB callback;
    
    insertPlaylist(PlaylistDAO playlistDAO, AnyEntityDAO anyEntityDAO,
                   Callback.InsertPlaylistCB callback) {
      this.playlistDAO = playlistDAO;
      this.anyEntityDAO = anyEntityDAO;
      this.callback = callback;
    }
    
    @Override
    protected Long doInBackground(final Playlist... params) {
      Playlist playlist = params[0];
      long     id       = playlistDAO.insert(playlist);
      
      if (id > -1) {
        anyEntityDAO.insert(new AnyEntity(id, playlist.getName(), "playlist", playlist.getName()));
      }
      
      return id;
    }
    
    @Override
    protected void onPostExecute(Long id) {
      if (id > 0) {
        callback.onResponse(true, id + "");
      } else {
        callback.onResponse(false, "Failed to create Playlist: Duplicate Name");
      }
    }
  }
  
  private static class insertPlaylistGroupTrack extends AsyncTask<Playlist_GroupTrack, Void, Void> {
    Playlist_GroupTrackDAO playlistGroupTrackDAO;
    
    insertPlaylistGroupTrack(Playlist_GroupTrackDAO playlistGroupTrackDAO) {
      this.playlistGroupTrackDAO = playlistGroupTrackDAO;
    }
    
    @Override
    protected Void doInBackground(final Playlist_GroupTrack... params) {
      Playlist_GroupTrack playlistGroupTrack = params[0];
      int sortOrder = playlistGroupTrackDAO.maxSortOrder(
          playlistGroupTrack.getPlaylistID()) + 1;
      playlistGroupTrack.setSortOrder(sortOrder);
      playlistGroupTrackDAO.insert(playlistGroupTrack);
      
      return null;
    }
  }
  
  private static class updatePlaylistGroupTrackSortOrder extends AsyncTask<Void, Void, Void> {
    Playlist_GroupTrackDAO playlistGroupTrackDAO;
    long                   playlistID;
    int                    oldSortOrder;
    int                    newSortOrder;
    
    updatePlaylistGroupTrackSortOrder(Playlist_GroupTrackDAO playlistGroupTrackDAO, long playlistID,
                                      int oldSortOrder,
                                      int newSortOrder) {
      this.playlistGroupTrackDAO = playlistGroupTrackDAO;
      this.playlistID = playlistID;
      this.oldSortOrder = oldSortOrder;
      this.newSortOrder = newSortOrder;
    }
    
    @Override
    protected Void doInBackground(Void... params) {
      // change conflicting item to a spoof sortOrder
      playlistGroupTrackDAO.updateSortOrder(playlistID, newSortOrder, 999999);
      // move the item
      playlistGroupTrackDAO.updateSortOrder(playlistID, oldSortOrder, newSortOrder);
      // swap the conflicting item with old spot
      playlistGroupTrackDAO.updateSortOrder(playlistID, 999999, oldSortOrder);
      
      return null;
    }
  }
  
  private static class deleteGroupTrackFromPlaylist extends AsyncTask<Void, Void, Void> {
    Playlist_GroupTrackDAO playlistGroupTrackDAO;
    long                   playlistID;
    long                   entityID;
    String                 entityType;
    int                    sortOrder;
    
    deleteGroupTrackFromPlaylist(Playlist_GroupTrackDAO playlistGroupTrackDAO, long playlistID,
                                 long entityID, String entityType, int sortOrder) {
      this.playlistGroupTrackDAO = playlistGroupTrackDAO;
      this.playlistID = playlistID;
      this.entityID = entityID;
      this.entityType = entityType;
      this.sortOrder = sortOrder;
    }
    
    @Override
    protected Void doInBackground(Void... params) {
      playlistGroupTrackDAO.delete(playlistID, entityID, entityType);
      playlistGroupTrackDAO.cascadeSortOrder(playlistID, sortOrder);
      
      return null;
    }
  }
}
