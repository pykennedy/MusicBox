package pyk.musicbox.contract.fragment;

public interface SearchFragmentContract {
  interface SearchListItemAdapterView {
    void applyFilters(boolean[] slicers);
  }
  
  interface SearchFragmentPresenter {
    void slicersChanged(SearchListItemAdapterView slia, boolean[] slicers);
  }
}
