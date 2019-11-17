package pyk.musicbox.contract.activity;

import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.fragment.base.BaseFragment;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
    void swapFragment(BaseFragment fragment, boolean replace);
    void swapTrack(long id, String name, long playlistID, boolean inGroup);
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
    void refreshTrackList(MainActivity context);
  }
  
  
}