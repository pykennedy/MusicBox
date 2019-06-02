package pyk.musicbox.presenter;

import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.model.DBRefresh;
import pyk.musicbox.view.activity.MainActivity;

public class MainActivityPresenter implements MainActivityContract.MainActivityPresenter {
  private MainActivityContract.MainActivityView activityView;
  
  public MainActivityPresenter(MainActivityContract.MainActivityView activityView) {
    this.activityView = activityView;
  }
  
  @Override public void tileTapped() {
    activityView.showToast();
  }
  
  @Override
  public void refreshTrackList(MainActivity context) {
    DBRefresh dbRefresh = new DBRefresh();
    dbRefresh.refreshTrackList(context);
  }
  
}
