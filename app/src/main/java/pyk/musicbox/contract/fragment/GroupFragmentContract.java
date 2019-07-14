package pyk.musicbox.contract.fragment;

public interface GroupFragmentContract {
  interface GroupFragmentPresenter {
    void getTracksInGroup(Long id);
  }
  
  interface GroupListItemAdapterView {
    void getTracksInGroup(Long id);
  }
}
