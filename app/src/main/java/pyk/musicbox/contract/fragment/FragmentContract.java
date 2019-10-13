package pyk.musicbox.contract.fragment;

import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.view.fragment.base.BaseFragment;

public interface FragmentContract {
  interface FragmentPresenter {
    void tileTapped(MainActivityContract.MainActivityView mainActivityView, BaseFragment fragment,
                    boolean replace);
    void swapTrack(MainActivityContract.MainActivityView mainActivityView, long id, String name);
  }
}
