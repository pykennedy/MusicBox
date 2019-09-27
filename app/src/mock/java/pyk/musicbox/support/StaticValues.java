package pyk.musicbox.support;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Playlist;
import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.entity.Track;

public class StaticValues {
  public static final List<Track>               trackList          = spoofedTracks();
  public static final List<Group>               groupList          = spoofedGroups();
  public static final List<Group_Track>         groupingsList      = spoofedGroupings();
  public static final List<Playlist>            playlistList       = spoofedPlaylists();
  public static final List<Playlist_GroupTrack> playlistItems      = spoofedPlaylistItems();
  public static final int                       totalTracks        = 26;
  public static final int                       totalAlbums        = 8;
  public static final int                       totalArtists       = 5;
  public static final int                       totalGroups        = 4;
  public static final int                       totalGroupings     = 12;
  public static final int                       totalPlaylists     = 3;
  public static final int                       TotalPlaylistItems = 15;
  
  private static List<Track> spoofedTracks() {
    List<Track> tracks = new ArrayList<>();
    
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(2, "End Game", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(3, "I Did Something Bad", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(4, "Don't Blame Me", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(5, "Delicate", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(6, "State of Grace", "Red", "Taylor Swift", "123"));
    tracks.add(new Track(7, "Red", "Red", "Taylor Swift", "123"));
    tracks.add(new Track(8, "Treacherous", "Red", "Taylor Swift", "123"));
    tracks.add(new Track(9, "I Knew You Were Trouble", "Red", "Taylor Swift", "123"));
    tracks.add(
        new Track(10, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 1. Rêveries, Passions", "07",
                  "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(11, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 2. Un Bal", "07",
                         "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(
        new Track(12, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 3. Scène Aux Champs", "07",
                  "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(13,
                         "Hector Berlioz: Symphonie Fantastique, Op. 14 - 5. Songe D'Une Nuit Du Sabbat",
                         "07", "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    tracks.add(
        new Track(14, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 1-",
                  "04", "Various Artists", "123"));
    tracks.add(new Track(15,
                         "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 2-Adagio",
                         "04", "Various Artists", "123"));
    tracks.add(new Track(16,
                         "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 3-Allegro",
                         "04", "Various Artists", "123"));
    tracks.add(new Track(17,
                         "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 4-menuetto -Trio -polonaise",
                         "04", "Various Artists", "123"));
    tracks.add(
        new Track(18, "Proprium Missae In Dedicatione Ecclesiae - Communio \"Domus Mea\"", "01",
                  "Benedictine Monks Of Münsterschwarzach", "123"));
    tracks.add(new Track(19,
                         "Proprium Missae In Dedicatione Ecclesiae - Introitus \"Terribilis Est Locus Iste\"",
                         "01", "Benedictine Monks Of Münsterschwarzach", "123"));
    tracks.add(new Track(20,
                         "Proprium Missae In Dedicatione Ecclesiae - Offertorium \"Domine Deus, In Simplicitate\"",
                         "01", "Benedictine Monks Of Münsterschwarzach", "123"));
    tracks.add(new Track(21, "Mark My Words", "Purpose", "Justin Bieber", "123"));
    tracks.add(new Track(22, "I'll Show You", "Purpose", "Justin Bieber", "123"));
    tracks.add(new Track(23, "What Do You Mean?", "Purpose", "Justin Bieber", "123"));
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(24, "Sorry", "Purpose", "Justin Bieber", "123"));
    tracks.add(new Track(25, "Fake 1", "Red", "Justin Bieber", "123"));
    tracks.add(new Track(26, "Fake 2", "Purpose", "Taylor Swift", "123"));
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    
    return tracks;
  }
  
  private static List<Group> spoofedGroups() {
    List<Group> groups = new ArrayList<>();
    
    groups.add(new Group("Brandenburg Concerto"));
    groups.add(new Group("Reputation"));
    groups.add(new Group("Purpose"));
    groups.add(new Group("aaa Empty Group"));
    
    // dupes
    groups.add(new Group("Reputation"));
    groups.add(new Group("Purpose"));
    
    return groups;
  }
  
  private static List<Group_Track> spoofedGroupings() {
    List<Group_Track> groupings = new ArrayList<>();
    
    // ideally always "Brandenburg Concerto"
    groupings.add(new Group_Track(1, 14));
    groupings.add(new Group_Track(1, 15));
    groupings.add(new Group_Track(1, 16));
    groupings.add(new Group_Track(1, 17));
    
    // ideally always "Reputation"
    groupings.add(new Group_Track(2, 1));
    groupings.add(new Group_Track(2, 2));
    groupings.add(new Group_Track(2, 3));
    groupings.add(new Group_Track(2, 4));
    groupings.add(new Group_Track(2, 5));
    
    // ideally always "Purpose"
    groupings.add(new Group_Track(3, 21));
    groupings.add(new Group_Track(3, 22));
    groupings.add(new Group_Track(3, 23));
    
    // dupes
    groupings.add(new Group_Track(1, 14));
    groupings.add(new Group_Track(2, 1));
    groupings.add(new Group_Track(3, 21));
    
    return groupings;
  }
  
  private static List<Playlist> spoofedPlaylists() {
    List<Playlist> playlists = new ArrayList<>();
    
    playlists.add(new Playlist("Taylor Swift Mix"));
    playlists.add(new Playlist("Justin Bieber Mix"));
    playlists.add(new Playlist("Empty Playlist"));
    
    return playlists;
  }
  
  private static List<Playlist_GroupTrack> spoofedPlaylistItems() {
    List<Playlist_GroupTrack> playlistItems = new ArrayList<>();
    
    // Taylor Swift stuff
    playlistItems.add(new Playlist_GroupTrack(1, 1, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 2, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 3, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 4, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 5, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 2, "group"));
    playlistItems.add(new Playlist_GroupTrack(1, 6, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 7, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 8, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 9, "track"));
    
    // Justin Bieber stuff
    playlistItems.add(new Playlist_GroupTrack(2, 21, "track"));
    playlistItems.add(new Playlist_GroupTrack(2, 22, "track"));
    playlistItems.add(new Playlist_GroupTrack(2, 23, "track"));
    playlistItems.add(new Playlist_GroupTrack(2, 24, "track"));
    playlistItems.add(new Playlist_GroupTrack(2, 3, "group"));
    
    // dupes
    playlistItems.add(new Playlist_GroupTrack(1, 5, "track"));
    playlistItems.add(new Playlist_GroupTrack(1, 2, "group"));
    playlistItems.add(new Playlist_GroupTrack(2, 24, "track"));
    playlistItems.add(new Playlist_GroupTrack(2, 3, "group"));
    
    return playlistItems;
  }
}
