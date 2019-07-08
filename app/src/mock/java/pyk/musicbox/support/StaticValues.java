package pyk.musicbox.support;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.model.entity.Track;

public class StaticValues {
  public static final List<Track> trackList   = spoofedTracks();
  public static final int         totalAlbums = 8;
  public static final int         totalArtists = 5;
  
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
    tracks.add(new Track(10, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 1. Rêveries, Passions", "07", "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(11, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 2. Un Bal", "07", "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(12, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 3. Scène Aux Champs", "07", "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(13, "Hector Berlioz: Symphonie Fantastique, Op. 14 - 5. Songe D'Une Nuit Du Sabbat", "07", "Lamoureux Concert Association Orchestra", "123"));
    tracks.add(new Track(1, "...Ready for It?", "Reputation", "Taylor Swift", "123"));
    tracks.add(new Track(14, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 1-", "04", "Various Artists", "123"));
    tracks.add(new Track(15, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 2-Adagio", "04", "Various Artists", "123"));
    tracks.add(new Track(16, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 3-Allegro", "04", "Various Artists", "123"));
    tracks.add(new Track(17, "Johann Sebastion Bach: Brandenburg Concerto No 1 in F major. BWV1046 4-menuetto -Trio -polonaise", "04", "Various Artists", "123"));
    tracks.add(new Track(18, "Proprium Missae In Dedicatione Ecclesiae - Communio \"Domus Mea\"", "01", "Benedictine Monks Of Münsterschwarzach", "123"));
    tracks.add(new Track(19, "Proprium Missae In Dedicatione Ecclesiae - Introitus \"Terribilis Est Locus Iste\"", "01", "Benedictine Monks Of Münsterschwarzach", "123"));
    tracks.add(new Track(20, "Proprium Missae In Dedicatione Ecclesiae - Offertorium \"Domine Deus, In Simplicitate\"", "01", "Benedictine Monks Of Münsterschwarzach", "123"));
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
}
