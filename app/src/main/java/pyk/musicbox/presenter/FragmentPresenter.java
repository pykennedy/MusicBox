package pyk.musicbox.presenter;

import android.support.v4.app.Fragment;

import pyk.musicbox.contract.fragment.FragmentContract;
import pyk.musicbox.contract.activity.MainActivityContract;

public class FragmentPresenter implements FragmentContract.FragmentPresenter {
  @Override
  public void tileTapped(MainActivityContract.MainActivityView mainActivityView, Fragment fragment,
                         boolean replace) {
      mainActivityView.swapFragment(fragment, replace);
  }
}

