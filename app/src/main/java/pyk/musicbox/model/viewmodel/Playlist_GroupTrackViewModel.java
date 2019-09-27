package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.repository.MBRepo;

public class Playlist_GroupTrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public Playlist_GroupTrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public void insert(Playlist_GroupTrack playlistGroupTrack) { repo.insert(playlistGroupTrack); }
  
  public void updatePlaylistGroupTrackSortOrder(long playlistID, int oldSortOrder, int newSortOrder) {
    repo.updatePlaylistGroupTrackSortOrder(playlistID, oldSortOrder, newSortOrder);
  }
  
  public void deleteGroupTrack(long playlistID, long entityID, String entityType, int sortOrder) {
    repo.deleteGroupTrackFromPlaylist(playlistID, entityID, entityType, sortOrder);
  }
  
  public LiveData<List<SortedEntity>> getItemsInPlaylist(long id) {
    return repo.getItemsInPlaylist(id);
  }
}
