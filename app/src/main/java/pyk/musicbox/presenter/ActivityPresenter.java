package pyk.musicbox.presenter;

import pyk.musicbox.contract.ActivityContract;

public class ActivityPresenter implements ActivityContract.ActivityPresenter {
  private ActivityContract.ActivityView activityView;
  
  public ActivityPresenter(ActivityContract.ActivityView activityView) {
    this.activityView = activityView;
  }
  
  @Override public void tileTapped() {
    activityView.showToast();
  }
}
