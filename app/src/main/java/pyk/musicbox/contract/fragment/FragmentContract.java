package pyk.musicbox.contract.fragment;

import android.support.v4.app.Fragment;

import pyk.musicbox.contract.activity.MainActivityContract;

public interface FragmentContract {
  interface FragmentPresenter {
    void tileTapped(MainActivityContract.MainActivityView mainActivityView, Fragment fragment,
                    boolean replace);
  }
}
