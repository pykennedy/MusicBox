package pyk.musicbox.model;

import android.arch.lifecycle.ViewModelProviders;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.entity.Playlist;
import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.GroupViewModel;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.model.viewmodel.PlaylistViewModel;
import pyk.musicbox.model.viewmodel.Playlist_GroupTrackViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.support.StaticValues;
import pyk.musicbox.view.activity.MainActivity;

public class DBRefresh {
  
  public void refreshTrackList(MainActivity context) {
    TrackViewModel trackViewModel = ViewModelProviders.of(context).get(
        TrackViewModel.class);
    GroupViewModel groupViewModel = ViewModelProviders.of(context).get(
        GroupViewModel.class);
    Group_TrackViewModel groupTrackViewModel = ViewModelProviders.of(context).get(
        Group_TrackViewModel.class);
    PlaylistViewModel            playlistViewModel           = ViewModelProviders.of(context).get(
        PlaylistViewModel.class);
    Playlist_GroupTrackViewModel playlistGroupTrackViewModel = ViewModelProviders.of(context).get(
        Playlist_GroupTrackViewModel.class);
    
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
    
    for (Playlist playlist : StaticValues.playlistList) {
      playlistViewModel.insert(playlist, new Callback.InsertPlaylistCB() {
        @Override public void onResponse(boolean succeeded, String msg) {
        
        }
      });
    }
    
    for (Playlist_GroupTrack playlistGroupTrack : StaticValues.playlistItems) {
      playlistGroupTrackViewModel.insert(playlistGroupTrack);
    }
  }
}
