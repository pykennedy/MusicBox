package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import pyk.musicbox.model.entity.AllEntities;
import pyk.musicbox.model.repository.MBRepo;

public class AllEntitiesViewModel extends AndroidViewModel {
  private MBRepo                      repo;
  private LiveData<List<AllEntities>> entities;
  
  public AllEntitiesViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
    List<String> entityTypes = Arrays.asList("track", "album", "artist", "group", "playlist");
    entities = repo.getAllEntities(entityTypes);
  }
  
  public LiveData<List<AllEntities>> getAllEntities(List<String> entityTypes) {
    entities = repo.getAllEntities(entityTypes);
    return repo.getAllEntities(entityTypes);
  }
}
