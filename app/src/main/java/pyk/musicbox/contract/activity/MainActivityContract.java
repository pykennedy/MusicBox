package pyk.musicbox.contract.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
    void swapFragment(Fragment fragment, boolean replace);
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
    void refreshTrackList(Context context);
  }
  
  
}