package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.repository.MBRepo;

public class Group_TrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public Group_TrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public void insert(Group_Track groupTrack) { repo.insert(groupTrack); }
  
  public void updateGroupTrackSortOrder(long groupID, int oldSortOrder, int newSortOrder) {
    repo.updateGroupTrackSortOrder(groupID, oldSortOrder, newSortOrder);
  }
}
