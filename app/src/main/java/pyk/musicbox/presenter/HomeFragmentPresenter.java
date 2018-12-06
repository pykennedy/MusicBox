package pyk.musicbox.presenter;

import pyk.musicbox.contract.HomeFragmentContract;
import pyk.musicbox.contract.MainActivityContract;

public class HomeFragmentPresenter implements HomeFragmentContract.HomeFragmentPresenter {
  HomeFragmentContract.HomeFragmentView homeFragmentView;
  
  public HomeFragmentPresenter(HomeFragmentContract.HomeFragmentView homeFragmentView) {
    this.homeFragmentView = homeFragmentView;
  }
  
  @Override
  public void tileTapped(MainActivityContract.MainActivityView mainActivityView, String fragment) {
    mainActivityView.swapFragment(fragment);
  }
}
