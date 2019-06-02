package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.Track;

public interface SearchListItemAdapterContract {
  public interface SearchListItemAdapterView {
    void triggerRefresh();
  }
  
  public interface SearchListItemAdapterPresenter {
    Track getTrackFromList(int i);
    int getItemCount();
  }
}
