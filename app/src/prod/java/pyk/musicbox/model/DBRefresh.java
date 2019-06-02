package pyk.musicbox.model;

import android.arch.lifecycle.ViewModelProviders;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class DBRefresh {
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel trackViewModel = ViewModelProviders.of(context).get(TrackViewModel.class);
    Uri            uri            = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    Cursor         cursor         = context.getContentResolver().query(uri, null, null, null, null);
    
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        do {
          Track track = new Track(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                                  cursor.getString(
                                      cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                                  null, null, null);
          trackViewModel.insert(track);
          Log.e("asdf", track.getName());
        } while (cursor.moveToNext());
      }
      cursor.close();
    }
  }
}
