package pyk.musicbox.contract.fragment;

public interface AlbumFragmentContract {
  interface  AlbumFragmentPresenter {
    void getTracksInAlbum(AlbumFragmentContract.AlbumListItemAdapterView adapterView, long id);
  }
  
  interface AlbumListItemAdapterView {
    void getTracksInAlbum(long id);
  }
}
