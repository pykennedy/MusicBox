package pyk.musicbox.contract.fragment;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.view.activity.MainActivity;

public interface SearchFragmentContract {
  interface SearchListItemAdapterView {
    void applyFilters(boolean[] slicers);
  }
  
  interface SearchFragmentPresenter {
    void slicersChanged(SearchListItemAdapterView slia, boolean[] slicers);
    void addGroup(MainActivity context, String name, Callback.InsertGroupCB callback);
  }
}
