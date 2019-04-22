package pyk.musicbox.support;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Track;

public class StaticValues {
  public static final ArrayList<Group> groupList = buildGroupList();
  public static final List<Track> trackList = spoofedTracks();
  
  private static ArrayList<Group> buildGroupList() {
    ArrayList<Group> groups = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      groups.add(new Group(i, "GroupOld #" + i));
    }
    return groups;
  }
  
  private static List<Track> spoofedTracks() {
    List<Track> tracks = new ArrayList<>();
    
    tracks.add(new Track(0, "...Ready for It?", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(1, "End Game", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(2, "I Did Something Bad", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(3, "Don't Blame Me", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(4, "Delicate", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(5, "State of Grace", "Taylor Swift", "Red", "123"));
    tracks.add(new Track(6, "Red", "Taylor Swift", "Red", "123"));
    tracks.add(new Track(7, "Treacherous", "Taylor Swift", "Red", "123"));
    tracks.add(new Track(8, "I Knew You Were Trouble", "Taylor Swift", "Red", "123"));
    tracks.add(new Track(9, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 1. Rêveries, Passions", "Lamoureux Concert Association Orchestra", "07", "123"));
    tracks.add(new Track(10, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 2. Un Bal", "Lamoureux Concert Association Orchestra", "07", "123"));
    tracks.add(new Track(11, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 3. Scène Aux Champs", "Lamoureux Concert Association Orchestra", "07", "123"));
    tracks.add(new Track(12, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 5. Songe D'Une Nuit Du Sabbat", "Lamoureux Concert Association Orchestra", "07", "123"));
    tracks.add(new Track(13, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 1-", "Various Artists", "04", "123"));
    tracks.add(new Track(14, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 2-Adagio", "Various Artists", "04", "123"));
    tracks.add(new Track(15, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 3-Allegro", "Various Artists", "04", "123"));
    tracks.add(new Track(16, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 4-menuetto -Trio -polonaise", "Various Artists", "04", "123"));
    tracks.add(new Track(17, "Proprium Missae In Dedicatione Ecclesiae - Communio \"Domus Mea\"", "Benedictine Monks Of Münsterschwarzach", "01", "123"));
    tracks.add(new Track(18, "Proprium Missae In Dedicatione Ecclesiae - Introitus \"Terribilis Est Locus Iste\"", "Benedictine Monks Of Münsterschwarzach", "01", "123"));
    tracks.add(new Track(19, "Proprium Missae In Dedicatione Ecclesiae - Offertorium \"Domine Deus, In Simplicitate\"", "Benedictine Monks Of Münsterschwarzach", "01", "123"));
    tracks.add(new Track(20, "Mark My Words", "Justin Bieber", "Purpose", "123"));
    tracks.add(new Track(21, "I'll Show You", "Justin Bieber", "Purpose", "123"));
    tracks.add(new Track(22, "What Do You Mean?", "Justin Bieber", "Purpose", "123"));
    tracks.add(new Track(23, "Sorry", "Justin Bieber", "Purpose", "123"));
    tracks.add(new Track(24, "Fake 1", "Justin Bieber", "Red", "123"));
    tracks.add(new Track(25, "Fake 2", "Taylor Swift", "Purpose", "123"));
    /*tracks.add(new Track(26, "...Ready for It?", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(27, "...Ready for It?", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(28, "...Ready for It?", "Taylor Swift", "Reputation", "123"));
    tracks.add(new Track(29, "...Ready for It?", "Taylor Swift", "Reputation", "123"));*/
    
    return tracks;
  }
  
  private static List<Group> spoofedGroups() {
    List<Group> groups = new ArrayList<>();
    
    groups.add(new Group(0, "Reputation"));
    groups.add(new Group(1, "Red"));
    
    return groups;
  }
}
