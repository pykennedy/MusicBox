package pyk.musicbox.presenter;

import pyk.musicbox.contract.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.MainActivityPresenter {
  private MainActivityContract.MainActivityView activityView;
  
  public MainActivityPresenter(MainActivityContract.MainActivityView activityView) {
    this.activityView = activityView;
  }
  
  @Override public void tileTapped() {
    activityView.showToast();
  }
}
