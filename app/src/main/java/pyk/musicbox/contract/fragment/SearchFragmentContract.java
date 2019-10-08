package pyk.musicbox.contract.fragment;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.view.activity.MainActivity;

public interface SearchFragmentContract {
  interface SearchListItemAdapterView {
    void applyFilters(boolean[] slicers);
    void search(boolean[] slicers, String text);
  }
  
  interface SearchFragmentPresenter {
    void slicersChanged(SearchListItemAdapterView slia, boolean[] slicers);
    void addGroup(MainActivity context, String name, Callback.InsertGroupCB callback);
    void addPlaylist(MainActivity context, String name, Callback.InsertPlaylistCB callback);
    void search(SearchListItemAdapterView slia, boolean[] slicers, String text);
  }
}
