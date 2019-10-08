package pyk.musicbox.presenter;

import android.arch.lifecycle.ViewModelProviders;

import java.util.List;

import pyk.musicbox.contract.fragment.AddToGroupFragmentContract;
import pyk.musicbox.model.entity.Group_Track;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class AddToGroupFragmentPresenter
    implements AddToGroupFragmentContract.AddToGroupFragmentPresenter {
  @Override
  public void slicersChanged(AddToGroupFragmentContract.AddToGroupListItemAdapterView adapterView,
                             boolean[] slicers) {
    adapterView.applyFilters(slicers);
  }
  
  @Override public void addToGroup(MainActivity context, Long groupID, List<Long> tracks) {
    Group_TrackViewModel groupTrackViewModel = ViewModelProviders.of(context).get(
        Group_TrackViewModel.class);
    for(Long trackID : tracks) {
      groupTrackViewModel.insert(new Group_Track(groupID, trackID));
    }
  }
  
  @Override public void search(AddToGroupFragmentContract.AddToGroupListItemAdapterView adapter,
                               boolean[] slicers, String text) {
    adapter.search(slicers, text);
  }
}
