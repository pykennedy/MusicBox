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
  private static final PlaylistManager               instance = new PlaylistManager();
  private              MainActivity                  context;
  private              TrackViewModel                tvm;
  private              MediatorLiveData<List<Track>> mediator = new MediatorLiveData<>();
  private              LiveData<List<Track>>         currentList;
  private              int                           index    = 0;
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
        get().initialized = true;
        callback.onComplete(true, "Initialized Playlist");
      }
    });
  }
  
  public static Track getNext() {
    get().index+= 1;
    return getCurrent();
  }
  
  public static Track getCurrent() {
    List<Track> list = get().mediator.getValue();
    return (list != null && get().index >= 0 && get().index < list.size()) ? list.get(get().index)
                                                                           : null;
  }
  
  public static Track getPrev() {
    get().index--;
    return getCurrent();
  }
}
