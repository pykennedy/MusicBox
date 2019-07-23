package pyk.musicbox.contract.adapter;

import pyk.musicbox.model.entity.SortedTrack;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void triggerRefresh();
  }
  
  interface GroupListItemAdapterPresenter {
    SortedTrack getTrackFromList(int i);
    int getItemCount();
    void getTracksInGroup(long id);
    void updateSortOrder(long id, int oldSortOrder, int newSortOrder);
  }
}
