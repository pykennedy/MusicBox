package pyk.musicbox.model;

import android.arch.lifecycle.ViewModelProviders;
import android.util.Log;

import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.support.StaticValues;
import pyk.musicbox.view.activity.MainActivity;

public class DBRefresh {
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel trackViewModel = ViewModelProviders.of(context).get(TrackViewModel.class);
  
    for(Track track : StaticValues.trackList) {
      trackViewModel.insert(track);
      Log.e("asdf", track.getName());
    }
  }
}
