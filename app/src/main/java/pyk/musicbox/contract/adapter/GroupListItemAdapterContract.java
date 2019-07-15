package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.Track;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface GroupListItemAdapterPresenter {
    Track getTrackFromList(int i);
    int getItemCount();
    void getTracksInGroup(long id);
  }
}
