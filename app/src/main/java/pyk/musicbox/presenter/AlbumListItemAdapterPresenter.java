package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.adapter.AlbumListItemAdapterContract;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.AlbumFragment;

public class AlbumListItemAdapterPresenter implements AlbumListItemAdapterContract.AlbumListItemAdapterPresenter {
  private AlbumListItemAdapterContract.AlbumListItemAdapterView adapterView;
  private TrackViewModel tvm;
  private MediatorLiveData<List<SortedTrack>> mediator = new MediatorLiveData<>();
  private LiveData<List<SortedTrack>> currentList;
  
  public AlbumListItemAdapterPresenter(final AlbumListItemAdapterContract.AlbumListItemAdapterView adapterView, final
                                       AlbumFragment context) {
    this.adapterView = adapterView;
    tvm = ViewModelProviders.of(context).get(TrackViewModel.class);
  
    mediator.observe(context, new Observer<List<SortedTrack>>() {
      @Override public void onChanged(@Nullable List<SortedTrack> tracks) {
        adapterView.triggerRefresh();
      }
    });
  }
  
  @Override public SortedTrack getTrackFromList(int i) {
    List<SortedTrack> list = mediator.getValue();
    return (list != null) ? list.get(i) : null;
  }
  
  @Override public int getItemCount() {
    List<SortedTrack> list = mediator.getValue();
    return (list != null) ? list.size() : 0;
  }
  
  @Override public void getTracksInAlbum(long id) {
    if (currentList != null) {
      mediator.removeSource(currentList);
    }
  
    currentList = tvm.getTracksInAlbum(id);
    mediator.addSource(currentList, new Observer<List<SortedTrack>>() {
      @Override public void onChanged(@Nullable List<SortedTrack> tracks) {
        mediator.setValue(tracks);
      }
    });
  }
}
