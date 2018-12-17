package pyk.musicbox.presenter;

import pyk.musicbox.contract.FragmentContract;
import pyk.musicbox.contract.MainActivityContract;

public class FragmentPresenter implements FragmentContract.FragmentPresenter {
  @Override
  public void tileTapped(MainActivityContract.MainActivityView mainActivityView, String fragment,
                         boolean replace) {
      mainActivityView.swapFragment(fragment, replace);
  }
}

