package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.repository.MBRepo;

public class Artist_AlbumTrackViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public Artist_AlbumTrackViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public LiveData<List<SortedEntity>> getItemsInArtist(long id) {
    return repo.getItemsInArtist(id);
  }
}
