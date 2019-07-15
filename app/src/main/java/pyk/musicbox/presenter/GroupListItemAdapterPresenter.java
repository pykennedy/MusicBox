package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.GroupFragment;

public class GroupListItemAdapterPresenter
    implements GroupListItemAdapterContract.GroupListItemAdapterPresenter {
  GroupListItemAdapterContract.GroupListItemAdapterView adapterView;
  private TrackViewModel                tvm;
  private MediatorLiveData<List<Track>> mediator = new MediatorLiveData<>();
  private LiveData<List<Track>>         currentList;
  
  public GroupListItemAdapterPresenter(
      final GroupListItemAdapterContract.GroupListItemAdapterView adapterView,
      final GroupFragment context) {
    this.adapterView = adapterView;
    tvm = ViewModelProviders.of(context).get(TrackViewModel.class);
    
    mediator.observe(context, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> tracks) {
        adapterView.triggerRefresh();
      }
    });
  }
  
  @Override public Track getTrackFromList(int i) {
    List<Track> list = mediator.getValue();
    return (list != null) ? list.get(i) : null;
  }
  
  @Override public int getItemCount() {
    List<Track> list = mediator.getValue();
    return (list != null) ? list.size() : 0;
  }
  
  @Override public void getTracksInGroup(long id) {
    if(currentList != null) {
      mediator.removeSource(currentList);
    }
  
    currentList = tvm.getTracksInGroup(id);
    mediator.addSource(currentList, new Observer<List<Track>>() {
      @Override public void onChanged(@Nullable List<Track> tracks) {
        mediator.setValue(tracks);
      }
    });
  }
}
