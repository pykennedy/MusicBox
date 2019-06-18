package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.SearchFragmentContract;

public class SearchFragmentPresenter implements SearchFragmentContract.SearchFragmentPresenter {
  @Override public void slicersChanged(SearchFragmentContract.SearchListItemAdapterView sfsliap,
                                       boolean[] slicers) {
    sfsliap.applyFilters(slicers);
  }
}
