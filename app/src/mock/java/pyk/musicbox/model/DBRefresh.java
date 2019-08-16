package pyk.musicbox.model;

import android.arch.lifecycle.ViewModelProviders;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.GroupViewModel;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.support.StaticValues;
import pyk.musicbox.view.activity.MainActivity;

public class DBRefresh {
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel       trackViewModel      = ViewModelProviders.of(context).get(
        TrackViewModel.class);
    GroupViewModel       groupViewModel      = ViewModelProviders.of(context).get(
        GroupViewModel.class);
    Group_TrackViewModel groupTrackViewModel = ViewModelProviders.of(context).get(
        Group_TrackViewModel.class);
    
    for (Track track : StaticValues.trackList) {
      trackViewModel.insert(track);
    }
    
    for (Group group : StaticValues.groupList) {
      groupViewModel.insert(group, new Callback.InsertGroupCB() {
        @Override public void onResponse(boolean succeeded, String msg) {
        
        }
      });
    }
    
    for (Group_Track groupTrack : StaticValues.groupingsList) {
      groupTrackViewModel.insert(groupTrack);
    }
  }
}
