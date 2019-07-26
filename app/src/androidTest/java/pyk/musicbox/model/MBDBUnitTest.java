package pyk.musicbox.model;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.io.IOException;
import java.util.List;

import pyk.musicbox.model.dao.AlbumDAO;
import pyk.musicbox.model.dao.Album_TrackDAO;
import pyk.musicbox.model.dao.AnyEntityDAO;
import pyk.musicbox.model.dao.ArtistDAO;
import pyk.musicbox.model.dao.Artist_AlbumTrackDAO;
import pyk.musicbox.model.dao.GroupDAO;
import pyk.musicbox.model.dao.Group_TrackDAO;
import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Album_Track;
import pyk.musicbox.model.entity.Artist;
import pyk.musicbox.model.entity.Artist_AlbumTrack;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.support.StaticValues;
import pyk.musicbox.utility.LiveDataTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MBDBUnitTest {
  private TrackDAO             trackDAO;
  private AlbumDAO             albumDAO;
  private ArtistDAO            artistDAO;
  private Album_TrackDAO       album_trackDAO;
  private Artist_AlbumTrackDAO artist_albumTrackDAO;
  private AnyEntityDAO         anyEntityDAO;
  private GroupDAO             groupDAO;
  private Group_TrackDAO       groupTrackDAO;
  private MBDB                 db;
  
  @Rule
  public TestRule rule = new InstantTaskExecutorRule();
  
  @Before
  public void createDB() {
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, MBDB.class)
             .allowMainThreadQueries()
             .build();
    trackDAO = db.trackDAO();
    albumDAO = db.albumDAO();
    artistDAO = db.artistDAO();
    album_trackDAO = db.album_trackDAO();
    artist_albumTrackDAO = db.artist_albumTrackDAO();
    anyEntityDAO = db.anyEntityDAO();
    groupDAO = db.groupDAO();
    groupTrackDAO = db.groupTrackDAO();
  }
  
  @After
  public void closeDB() throws IOException {
    db.close();
  }
  
  @Test
  public void writeAndReadTrack() {
    Track track = StaticValues.trackList.get(0);
    trackDAO.insert(track);
    Track track2 = trackDAO.getTrackByID(track.getId());
    assertEquals(track2.getId(), track.getId());
  }
  
  @Test
  public void writeAllTracksNoDupes() throws InterruptedException {
    for (Track track : StaticValues.trackList) {
      long   id       = track.getId();
      String name     = track.getName();
      String artist   = track.getArtist();
      String album    = track.getAlbum();
      String duration = track.getDuration();
      
      Track inserting = new Track(id, name, artist, album, duration);
      trackDAO.insert(inserting);
    }
    
    List<Track> tracks = LiveDataTestUtil.getValue(trackDAO.getAllTracks());
    assertEquals(StaticValues.totalTracks, tracks.size());
  }
  
  @Test
  public void writeAndReadAlbum() {
    Track track = StaticValues.trackList.get(0);
    Album album = new Album(track.getAlbum(), track.getArtist(),
                            track.getAlbum() + track.getArtist());
    albumDAO.insert(album);
    Album album2 = albumDAO.getAlbumByKey(track.getAlbum() + track.getArtist());
    assertEquals(album2.getKey(), album.getKey());
  }
  
  @Test
  public void writeAllAlbumsNoDupes() throws InterruptedException {
    for (Track track : StaticValues.trackList) {
      String album  = track.getAlbum();
      String artist = track.getArtist();
      String key    = track.getAlbum() + track.getArtist();
      
      Album inserting = new Album(album, artist, key);
      albumDAO.insert(inserting);
    }
    
    List<Album> albums = LiveDataTestUtil.getValue(albumDAO.getAllAlbums());
    assertEquals(StaticValues.totalAlbums, albums.size());
  }
  
  @Test
  public void writeAndReadArtist() {
    Track  track  = StaticValues.trackList.get(0);
    Artist artist = new Artist(track.getArtist());
    artistDAO.insert(artist);
    Artist artist2 = artistDAO.getArtistByName(track.getArtist());
    assertEquals(artist2.getName(), artist.getName());
  }
  
  @Test
  public void writeAllArtistsNoDupes() throws InterruptedException {
    for (Track track : StaticValues.trackList) {
      String artist = track.getArtist();
      
      Artist inserting = new Artist(artist);
      artistDAO.insert(inserting);
    }
    
    List<Artist> artists = LiveDataTestUtil.getValue(artistDAO.getAllArtists());
    assertEquals(StaticValues.totalArtists, artists.size());
  }
  
  @Test
  public void allRelationshipsMade() throws InterruptedException {
    Track  track  = StaticValues.trackList.get(0);
    String name   = track.getName();
    String album  = track.getAlbum();
    String artist = track.getArtist();
    
    long trackID  = trackDAO.insert(track);
    long albumID  = albumDAO.insert(new Album(album, artist, album + artist));
    long artistID = artistDAO.insert(new Artist(artist));
    
    album_trackDAO.insert(new Album_Track(albumID, trackID));
    artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, trackID, "track"));
    artist_albumTrackDAO.insert(new Artist_AlbumTrack(artistID, albumID, "album"));
    
    List<Track> tracks = LiveDataTestUtil.getValue(album_trackDAO.getTracksByAlbumID(albumID));
    assertEquals(track.getId(), tracks.get(0).getId());
    
    tracks = LiveDataTestUtil.getValue(artist_albumTrackDAO.getTracksByArtistID(artistID));
    assertEquals(track.getId(), tracks.get(0).getId());
    
    List<Album> albums = LiveDataTestUtil.getValue(
        artist_albumTrackDAO.getAlbumsByArtistID(artistID));
    assertEquals(albumID, albums.get(0).getId());
  }
  
  @Test
  public void writeAndReadGroup() {
    Group group  = StaticValues.groupList.get(0);
    long  id     = groupDAO.insert(group);
    Group group2 = groupDAO.getGroupByID(id);
    assertEquals(group2.getId(), id);
  }
  
  @Test
  public void writeAllGroupsNoDupes() throws InterruptedException {
    for (Group group : StaticValues.groupList) {
      groupDAO.insert(group);
    }
    
    List<Group> groups = LiveDataTestUtil.getValue(groupDAO.getAllGroups());
    assertEquals(StaticValues.totalGroups, groups.size());
  }
  
  @Test
  public void writeAndReadGrouping() {
    Group_Track groupTrack = StaticValues.groupingsList.get(0);
    groupTrackDAO.insert(groupTrack);
    Group_Track groupTrack2 = groupTrackDAO.getGroupTrackByIDs(groupTrack.getGroupID(),
                                                               groupTrack.getTrackID());
    assertEquals(groupTrack2.getGroupID(), groupTrack.getGroupID());
    assertEquals(groupTrack2.getTrackID(), groupTrack.getTrackID());
  }
  
  @Test
  public void writeAllGroupingsNoDupes() throws InterruptedException {
    for (Group_Track groupTrack : StaticValues.groupingsList) {
      groupTrackDAO.insert(groupTrack);
    }
    
    List<Group_Track> groupings = LiveDataTestUtil.getValue(groupTrackDAO.getAllGroupTracks());
    assertEquals(StaticValues.totalGroupings, groupings.size());
  }
  
  @Test
  public void deleteGrouping() throws InterruptedException {
    for (Group_Track groupTrack : StaticValues.groupingsList) {
      groupTrackDAO.insert(groupTrack);
    }
    groupTrackDAO.delete(StaticValues.groupingsList.get(0).getGroupID(),
                         StaticValues.groupingsList.get(0).getTrackID());
    
    List<Group_Track> groupings = LiveDataTestUtil.getValue(groupTrackDAO.getAllGroupTracks());
    
    Group_Track groupTrack = groupTrackDAO.getGroupTrackByIDs(
        StaticValues.groupingsList.get(0).getGroupID(),
        StaticValues.groupingsList.get(0).getTrackID());
    
    // doesn't exist
    assertNull(groupTrack);
    // list shrank by exactly 1
    assertEquals(StaticValues.totalGroupings - 1, groupings.size());
  }
}
