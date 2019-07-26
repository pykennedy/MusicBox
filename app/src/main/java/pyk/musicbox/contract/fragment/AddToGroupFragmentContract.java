package pyk.musicbox.contract.fragment;

import java.util.List;

import pyk.musicbox.view.activity.MainActivity;

public interface AddToGroupFragmentContract {
  interface AddToGroupListItemAdapterView {
    void applyFilters(boolean[] slicers);
  }
  
  interface AddToGroupFragmentPresenter {
    void slicersChanged(AddToGroupFragmentContract.AddToGroupListItemAdapterView adapterView, boolean[] slicers);
    void addToGroup(MainActivity context, Long groupID, List<Long> TrackID);
  }
}
