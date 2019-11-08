package pyk.musicbox.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class PlaylistManager {
  private static final PlaylistManager               instance    = new PlaylistManager();
  private              MainActivity                  context;
  private              TrackViewModel                tvm;
  private              MediatorLiveData<List<Track>> mediator    = new MediatorLiveData<>();
  private              LiveData<List<Track>>         currentList;
  private              List<Track>                   list;
  private              int                           index       = 0;
  private              boolean                       initialized = false;
  
  private PlaylistManager()           {}
  
  public static PlaylistManager get() { return instance; }
  
  public static void setContext(MainActivity context) {
    get().context = context;
    
    get().mediator.observe(context, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> tracks) {
        // TODO: maybe do logic here idk
      }
    });
  }
  
  public static void initPlaylist(final Callback.InitPlaylistCB callback) {
    if (get().context == null) {
      callback.onComplete(false, "Failed. Try Again.");
      return;
    }
    
    get().initialized = false;
    
    get().tvm = ViewModelProviders.of(get().context).get(TrackViewModel.class);
    
    if (get().currentList != null) {
      get().mediator.removeSource(get().currentList);
    }
    
    get().currentList = get().tvm.getAllTracks();
    get().mediator.addSource(get().currentList, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> tracks) {
        get().mediator.setValue(tracks);
        configList(callback);
        callback.onComplete(true, "Initialized Playlist");
      }
    });
  }
  
  public static void configList(final Callback.InitPlaylistCB callback) {
    get().list = get().mediator.getValue();
    // todo: sort by sort order, shuffle, etc.
    get().initialized = true;
    callback.onComplete(true, "Initialized Playlist");
  }
  
  public static void moveHead(long trackID, Callback.moveHeadCB callback) {
    List<Track> list = get().list;
    for(int i = 0; i < list.size(); i++) {
      Track track = list.get(i);
      if(track.getId() == trackID) {
        get().index = i;
        callback.onComplete(true, "found");
        return;
      }
    }
  }
  
  public static Track getNext() {
    get().index += 1;
    return getCurrent();
  }
  
  public static Track getCurrent() {
    List<Track> list = get().mediator.getValue();
    
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
  
  public static Track getPrev() {
    get().index -= 1;
    return getCurrent();
  }
}
