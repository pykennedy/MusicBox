package pyk.musicbox.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import pyk.musicbox.model.entity.Track;

public class TrackList {
  private static final TrackList        instance = new TrackList();
  private              ArrayList<Track> tracks;
  
  public static TrackList getInstance() {
    return instance;
  }
  
  private TrackList() {
    tracks = new ArrayList<>();
  }
  
  public int getCount() {
    return tracks.size();
  }
  
  public ArrayList<Track> getTracks() { return tracks; }
  
  public void populateTrackList(Context context) {
    Uri    uri    = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
    
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        do {
          Track track = new Track(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                                  cursor.getString(
                                      cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                                  null, null, null);
          tracks.add(track);
          
          Log.e("asdf", track.getName());
        } while (cursor.moveToNext());
      }
      cursor.close();
    }
  }
}
