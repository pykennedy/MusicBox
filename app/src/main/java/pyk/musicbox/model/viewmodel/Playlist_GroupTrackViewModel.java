package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.repository.MBRepo;

public class Playlist_GroupTrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public Playlist_GroupTrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public void insert(Playlist_GroupTrack playlistGroupTrack) { repo.insert(playlistGroupTrack); }
  
  public void updatePlaylistGroupTrackSortOrder(long playlistID, int oldSortOrder, int newSortOrder) {
    repo.updateGroupTrackSortOrder(playlistID, oldSortOrder, newSortOrder);
  }
  
  public void deleteGroupTrack(long playlistID, long entityID, int sortOrder) {
    repo.deleteTrackFromGroup(playlistID, entityID, sortOrder);
  }
}
