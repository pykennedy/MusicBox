package pyk.musicbox.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.PlaybackEntity;
import pyk.musicbox.model.entity.PlaybackGrouping;
import pyk.musicbox.model.entity.PlaybackTrack;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class PlaylistManager {
  private static final PlaylistManager               instance    = new PlaylistManager();
  private              MainActivity                  context;
  private              LiveData<List<Track>>         currentList;
  private              List<PlaybackTrack>                   list;
  private              int                           index       = 0;
  private              boolean                       initialized = false;
  
  private PlaylistManager()           {}
  
  public static PlaylistManager get() { return instance; }
  
  public static void setContext(MainActivity context) {
    get().context = context;
  }
  
  public static void initPlaylist(final Callback.InitPlaylistCB callback, long playlistID) {
    if (get().context == null) {
      callback.onComplete(false, "Failed. Try Again.");
      return;
    }
    
    get().initialized = false;
    
    final TrackViewModel tvm = ViewModelProviders.of(
        get().context).get(TrackViewModel.class);
    final MediatorLiveData<List<PlaybackEntity>>   mediatorEntities = new MediatorLiveData<>();
    final MediatorLiveData<List<Track>>            mediatorTracks   = new MediatorLiveData<>();
    final MediatorLiveData<List<PlaybackGrouping>> mediatorGroups   = new MediatorLiveData<>();
    
    mediatorEntities.observe(get().context, new Observer<List<PlaybackEntity>>() {
      @Override public void onChanged(@Nullable List<PlaybackEntity> tracks) {}
    });
    
    mediatorGroups.observe(get().context, new Observer<List<PlaybackGrouping>>() {
      @Override public void onChanged(@Nullable List<PlaybackGrouping> groupings) {}
    });
  
    mediatorTracks.observe(get().context, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> groupings) {}
    });
    
    mediatorEntities.addSource(tvm.getAllPlaybackEntities(playlistID), new Observer<List<PlaybackEntity>>() {
      @Override public void onChanged(@Nullable List<PlaybackEntity> entities) {
        mediatorEntities.setValue(entities);
        final List<PlaybackEntity> pe = mediatorEntities.getValue();
        
        List<Long>       groupIDs = new ArrayList<>();
        final List<Long> trackIDs = new ArrayList<>();
        
        for (PlaybackEntity entity : pe) {
          if (entity.getEntityType().equals("group")) {
            groupIDs.add(entity.getEntityID());
          } else {
            trackIDs.add(entity.getEntityID());
          }
        }
        
        mediatorGroups.addSource(tvm.getAllPlaybackGroupings(groupIDs),
                                 new Observer<List<PlaybackGrouping>>() {
                                   @Override public void onChanged(
                                       @Nullable List<PlaybackGrouping> groupings) {
                                     mediatorGroups.setValue(groupings);
                                     final List<PlaybackGrouping> pg = mediatorGroups.getValue();
            
                                     mediatorTracks.addSource(tvm.getTracks(trackIDs),
                                                              new Observer<List<Track>>() {
                                                                @Override public void onChanged(
                                                                    @Nullable List<Track> tracks) {
                                                                  mediatorTracks.setValue(tracks);
                                                                  List<Track> t =
                                                                      mediatorTracks.getValue();
                
                                                                  configList(callback, pe, pg, t);
                                                                  // TODO: dump this into playlist manager to make a full list
                                                                }
                                                              });
                                   }
                                 });
      }
    });
    
    
  }
  
  public static void configList(final Callback.InitPlaylistCB callback,
                                List<PlaybackEntity> playbackEntities,
                                List<PlaybackGrouping> groupings, List<Track> tracks) {
    List<PlaybackTrack> temp = new ArrayList<>();
    
    for (PlaybackEntity entity : playbackEntities) {
      if (entity.getEntityType().equals("track")) {
        Long trackID = entity.getEntityID();
        for (Track track : tracks) {
          if (track.getId() == trackID) {
            temp.add(new PlaybackTrack(-1, null, track));
          }
        }
      } else {
        Long groupID = entity.getEntityID();
        for(PlaybackGrouping group : groupings) {
          if(group.getGroupID() == groupID) {
            temp.add(new PlaybackTrack(group.getGroupID(), group.getGroupName(), group.getTrack()));
          }
        }
      }
    }
    
    
    get().list = temp;
    // todo: sort by sort order, shuffle, etc.
    get().initialized = true;
    callback.onComplete(true, "Initialized Playlist");
  }
  
  public static void moveHead(long trackID, Callback.moveHeadCB callback) {
    List<PlaybackTrack> list = get().list;
    for (int i = 0; i < list.size(); i++) {
      PlaybackTrack track = list.get(i);
      if (track.getTrack().getId() == trackID) {
        get().index = i;
        callback.onComplete(true, "found");
        return;
      }
    }
  }
  
  public static PlaybackTrack getNext() {
    get().index += 1;
    return getCurrent();
  }
  
  public static PlaybackTrack getCurrent() {
    List<PlaybackTrack> list = get().list;
    
    if (list != null) {
      if (get().index < 0) {
        get().index = list.size() - 1;
      } else if (get().index >= list.size()) {
        get().index = 0;
      }
      
      return list.get(get().index);
    } else {
      return null;
    }
  }
  
  public static PlaybackTrack getPrev() {
    get().index -= 1;
    return getCurrent();
  }
}
