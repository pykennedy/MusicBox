package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.repository.MBRepo;

public class GroupViewModel extends AndroidViewModel {
  private MBRepo repo;
  
  public GroupViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
  }
  
  public void insert(Group group, Callback.InsertGroupCB callback) { repo.insert(group, callback); }
}
