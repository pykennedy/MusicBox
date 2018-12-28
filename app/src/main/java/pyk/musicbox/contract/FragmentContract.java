package pyk.musicbox.contract;

import android.support.v4.app.Fragment;

public interface FragmentContract {
  interface FragmentPresenter {
    void tileTapped(MainActivityContract.MainActivityView mainActivityView, Fragment fragment,
                    boolean replace);
  }
}
