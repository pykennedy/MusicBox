package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pyk.musicbox.model.entity.PlaybackEntity;
import pyk.musicbox.model.entity.PlaybackGrouping;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.repository.MBRepo;

public class TrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  //private LiveData<List<Track>> tracks;
  
  public TrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
    //tracks = repo.getAllTracks();
  }
  
  public LiveData<List<Track>> getAllTracks() { return repo.getAllTracks(); }
  
  public LiveData<List<SortedTrack>> getTracksInGroup(Long id) { return repo.getTracksInGroup(id); }
  
  public LiveData<List<SortedTrack>> getTracksInAlbum(Long id) { return repo.getTracksInAlbum(id); }
  
  public LiveData<List<PlaybackEntity>> getAllPlaybackEntities(long playlistID) {
    if(playlistID >= 0) {
      return repo.getPlaybackEntitiesInPlaylist(playlistID);
    } else {
      return repo.getAllPlaybackEntities();
    }
  }
  
  public LiveData<List<PlaybackGrouping>> getAllPlaybackGroupings(List<Long> groupIDs) { return repo.getAllPlaybackGroupings(groupIDs); }
  
  public LiveData<List<Track>> getTracks(List<Long> trackIDs)                          { return repo.getTracks(trackIDs); }
  
  public void insert(Track track)                                                      { repo.insert(track); }
}
