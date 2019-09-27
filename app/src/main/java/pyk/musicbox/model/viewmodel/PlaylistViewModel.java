package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Playlist;
import pyk.musicbox.model.repository.MBRepo;

public class PlaylistViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public PlaylistViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public void insert(Playlist playlist, Callback.InsertPlaylistCB callback) {
    repo.insert(playlist, callback);
  }
}
