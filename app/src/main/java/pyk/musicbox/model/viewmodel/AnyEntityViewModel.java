package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.repository.MBRepo;

public class AnyEntityViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public AnyEntityViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public LiveData<List<AnyEntity>> getAllEntities(List<String> entityTypes) {
    Log.e("debugging:", "getallentities " + entityTypes.toString());
    return repo.getAllEntities(entityTypes);
  }
  
}
