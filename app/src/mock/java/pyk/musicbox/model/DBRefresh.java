package pyk.musicbox.model;

import android.arch.lifecycle.ViewModelProviders;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.GroupViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.support.StaticValues;
import pyk.musicbox.view.activity.MainActivity;

public class DBRefresh {
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel trackViewModel = ViewModelProviders.of(context).get(TrackViewModel.class);
    GroupViewModel groupViewModel = ViewModelProviders.of(context).get(GroupViewModel.class);
  
    groupViewModel.insert(new Group("abcdefg"), new Callback.InsertGroupCB() {
      @Override public void onResponse(boolean succeeded, String msg) {
      
      }
    });
    
    for(Track track : StaticValues.trackList) {
      trackViewModel.insert(track);
    }
    
  }
}
