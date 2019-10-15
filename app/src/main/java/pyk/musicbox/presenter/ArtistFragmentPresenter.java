package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.ArtistFragmentContract;

public class ArtistFragmentPresenter extends FragmentPresenter implements ArtistFragmentContract.ArtistFragmentPresenter {
  @Override public void getEntitiesInArist(ArtistFragmentContract.ArtistListItemAdapterView adapter,
                                           long id) {
    adapter.getEntitiesInArtist(id);
  }
}
