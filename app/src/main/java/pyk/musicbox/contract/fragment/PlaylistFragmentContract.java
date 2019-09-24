package pyk.musicbox.contract.fragment;

public interface PlaylistFragmentContract {
  interface PlaylistFragmentPresenter {
    void getEntitiesInPlaylist(PlaylistFragmentContract.PlaylistListItemAdapterView adapterView,
                               Long id);
  }
  
  interface PlaylistListItemAdapterView {
    void getEntitiesInPlaylist(Long id);
  }
}
