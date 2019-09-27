package pyk.musicbox.presenter;

import pyk.musicbox.contract.fragment.PlaylistFragmentContract;

public class PlaylistFragmentPresenter extends FragmentPresenter
    implements PlaylistFragmentContract.PlaylistFragmentPresenter {
  @Override public void getEntitiesInPlaylist(
      PlaylistFragmentContract.PlaylistListItemAdapterView adapterView, Long id) {
    adapterView.getEntitiesInPlaylist(id);
  }
}
