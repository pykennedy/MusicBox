package pyk.musicbox.model;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import pyk.musicbox.model.dao.TrackDAO;
import pyk.musicbox.model.database.MBDB;
import pyk.musicbox.model.entity.Track;

import static org.junit.Assert.assertEquals;

public class MBDBTest {
  private TrackDAO trackDAO;
  private MBDB     db;
  
  @Before
  public void createDB() {
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, MBDB.class).build();
    trackDAO = db.trackDAO();
  }
  
  @After
  public void closeDB() throws IOException {
    db.close();
  }
  
  @Test
  public void writeAndReadTrack() throws Exception {
    Track track = new Track(3, "track 3");
    trackDAO.insert(track);
    Track track2 = trackDAO.getTrackByID(3);
    assertEquals(track2.getId(), track.getId());
  }
}
