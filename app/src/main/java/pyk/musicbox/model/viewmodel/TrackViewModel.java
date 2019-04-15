package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.repository.MBRepo;

public class TrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  private LiveData<List<Track>> tracks;
  
  public TrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
    tracks = repo.getAllTracks();
  }
  
  public LiveData<List<Track>> getAllTracks() { return tracks; }
  
  public void insert(Track track) { repo.insert(track); }
}
