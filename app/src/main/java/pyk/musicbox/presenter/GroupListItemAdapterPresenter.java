package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.GroupFragment;

public class GroupListItemAdapterPresenter
    implements GroupListItemAdapterContract.GroupListItemAdapterPresenter {
  GroupListItemAdapterContract.GroupListItemAdapterView adapterView;
  private TrackViewModel                      tvm;
  private Group_TrackViewModel                gtvm;
  private MediatorLiveData<List<SortedTrack>> mediator = new MediatorLiveData<>();
  private LiveData<List<SortedTrack>>         currentList;
  
  public GroupListItemAdapterPresenter(
      final GroupListItemAdapterContract.GroupListItemAdapterView adapterView,
      final GroupFragment context) {
    this.adapterView = adapterView;
    tvm = ViewModelProviders.of(context).get(TrackViewModel.class);
    gtvm = ViewModelProviders.of(context).get(Group_TrackViewModel.class);
    
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
  
  @Override public void getTracksInGroup(long id) {
    if (currentList != null) {
      mediator.removeSource(currentList);
    }
    
    currentList = tvm.getTracksInGroup(id);
    mediator.addSource(currentList, new Observer<List<SortedTrack>>() {
      @Override public void onChanged(@Nullable List<SortedTrack> tracks) {
        mediator.setValue(tracks);
      }
    });
  }
  
  @Override public void updateSortOrder(long id, int oldSortOrder, int newSortOrder) {
    gtvm.updateGroupTrackSortOrder(id, oldSortOrder, newSortOrder);
  }
  
  @Override public void removeTrack(long groupID, long trackID, int sortOrder) {
    gtvm.deleteTrack(groupID, trackID, sortOrder);
  }
}
