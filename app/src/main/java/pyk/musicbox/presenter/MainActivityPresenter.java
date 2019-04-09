package pyk.musicbox.presenter;

import android.content.Context;
import android.util.Log;

import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.model.TrackList;
import pyk.musicbox.model.dbobjects.Track;

public class MainActivityPresenter implements MainActivityContract.MainActivityPresenter {
  private MainActivityContract.MainActivityView activityView;
  
  public MainActivityPresenter(MainActivityContract.MainActivityView activityView) {
    this.activityView = activityView;
  }
  
  @Override public void tileTapped() {
    activityView.showToast();
  }
  
  @Override public void refreshTrackList(Context context) {
    TrackList.getInstance().populateTrackList(context);
    for (Track t : TrackList.getInstance().getTracks()) {
      Log.e("asdf", t.getTrackName());
    }
  }
  
}
