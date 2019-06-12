package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.SearchFragmentContract;

public class SearchFragmentPresenter implements SearchFragmentContract.SearchFragmentPresenter {
  @Override public void slicersChanged(SearchFragmentContract.SearchFragment_SLIAP sfsliap,
                                       boolean[] slicers) {
    sfsliap.applyFilters(slicers);
  }
}
