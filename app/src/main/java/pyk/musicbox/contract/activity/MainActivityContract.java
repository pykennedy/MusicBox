package pyk.musicbox.contract.activity;

import android.support.v4.app.Fragment;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
    void swapFragment(Fragment fragment, boolean replace);
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
  }
  
  
}