package pyk.musicbox.contract.fragment;

public interface SearchFragmentContract {
  interface SearchFragment_SLIAP {
    void applyFilters(boolean[] slicers);
  }
  
  interface SearchFragmentPresenter {
    void slicersChanged(SearchFragment_SLIAP sfsliap, boolean[] slicers);
  }
}
