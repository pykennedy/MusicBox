package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.AlbumFragmentContract;

public class AlbumFragmentPresenter extends FragmentPresenter implements AlbumFragmentContract.AlbumFragmentPresenter {
  @Override public void getTracksInAlbum(AlbumFragmentContract.AlbumListItemAdapterView adapterView,
                                         long id) {
    adapterView.getTracksInAlbum(id);
  }
}
