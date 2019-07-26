package pyk.musicbox.contract.fragment;

public interface GroupFragmentContract {
  interface GroupFragmentPresenter {
    void getTracksInGroup(GroupFragmentContract.GroupListItemAdapterView adapterView, Long id);
  }
  
  interface GroupListItemAdapterView {
    void getTracksInGroup(Long id);
  }
}
