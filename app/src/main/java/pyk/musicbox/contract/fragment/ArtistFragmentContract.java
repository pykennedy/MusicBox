package pyk.musicbox.contract.fragment;

public interface ArtistFragmentContract {
  interface ArtistFragmentPresenter {
    void getEntitiesInArist(ArtistFragmentContract.ArtistListItemAdapterView adapter, long id);
  }
  
  interface ArtistListItemAdapterView {
    void getEntitiesInArtist(long id);
  }
}
