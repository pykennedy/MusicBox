package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.GroupFragmentContract;

public class GroupFragmentPresenter extends FragmentPresenter
    implements GroupFragmentContract.GroupFragmentPresenter {
  
  @Override public void getTracksInGroup(GroupFragmentContract.GroupListItemAdapterView adapterView,
                                         Long id) {
    adapterView.getTracksInGroup(id);
  }
}
