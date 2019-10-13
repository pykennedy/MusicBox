package pyk.musicbox.presenter;

import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.contract.fragment.FragmentContract;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class FragmentPresenter implements FragmentContract.FragmentPresenter {
  @Override
  public void tileTapped(MainActivityContract.MainActivityView mainActivityView, BaseFragment fragment,
                         boolean replace) {
      mainActivityView.swapFragment(fragment, replace);
  }
  
  @Override
  public void swapTrack(MainActivityContract.MainActivityView mainActivityView, long id, String name) {
    mainActivityView.swapTrack(id, name);
  }
}

