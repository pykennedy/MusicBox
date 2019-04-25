package pyk.musicbox.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import pyk.musicbox.model.entity.Album;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.repository.MBRepo;

public class AlbumViewModel extends AndroidViewModel {
  private MBRepo                repo;
  private LiveData<List<Album>> albums;
  
  public AlbumViewModel(Application application) {
    super(application);
    repo = new MBRepo(application);
    albums = repo.getAllAlbums();
  }
  
  public LiveData<List<Album>> getAllAlbums() { return albums; }
  
  public void insert(Track track) { repo.insert(track); }
}
