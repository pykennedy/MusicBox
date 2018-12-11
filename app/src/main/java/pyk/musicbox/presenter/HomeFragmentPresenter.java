package pyk.musicbox.presenter;

import pyk.musicbox.contract.HomeFragmentContract;

public class HomeFragmentPresenter extends FragmentPresenter implements HomeFragmentContract.HomeFragmentPresenter {
  HomeFragmentContract.HomeFragmentView homeFragmentView;
  
  public HomeFragmentPresenter(HomeFragmentContract.HomeFragmentView homeFragmentView) {
    this.homeFragmentView = homeFragmentView;
  }
}
