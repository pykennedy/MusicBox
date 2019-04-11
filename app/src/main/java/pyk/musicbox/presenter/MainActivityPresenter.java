package pyk.musicbox.presenter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class MainActivityPresenter implements MainActivityContract.MainActivityPresenter {
  private MainActivityContract.MainActivityView activityView;
  
  public MainActivityPresenter(MainActivityContract.MainActivityView activityView) {
    this.activityView = activityView;
  }
  
  @Override public void tileTapped() {
    activityView.showToast();
  }
  
  @Override public void refreshTrackList(Context context) {
  
  }
  
//  @Override public void refreshTrackListOld(Context context) {
//    TrackList.getInstance().populateTrackList(context);
//    for (Track t : TrackList.getInstance().getTracks()) {
//      Log.e("asdf", t.getTrackName());
//    }
//  }
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel trackViewModel = ViewModelProviders.of(context).get(TrackViewModel.class);
    Uri    uri    = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
  
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        do {
          Track track = new Track(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                                  cursor.getString(
                                      cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
          //tracks.add(track);
        
          Log.e("asdf", track.getTrackName());
        } while (cursor.moveToNext());
      }
      cursor.close();
    }
  }
  
}
