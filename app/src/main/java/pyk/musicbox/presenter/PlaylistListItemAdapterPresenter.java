package pyk.musicbox.presenter;

import pyk.musicbox.contract.adapter.PlaylistListItemAdapterContract;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.viewmodel.GroupViewModel;
import pyk.musicbox.model.viewmodel.Playlist_GroupTrackViewModel;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.view.fragment.PlaylistFragment;

public class PlaylistListItemAdapterPresenter implements PlaylistListItemAdapterContract.PlaylistListItemAdapterPresenter {
  private PlaylistListItemAdapterContract.PlaylistListItemAdapterView adapterView;
  private Playlist_GroupTrackViewModel pgtvm;
  private TrackViewModel tvm;
  private GroupViewModel gvm;
  
  public PlaylistListItemAdapterPresenter(final PlaylistListItemAdapterContract.PlaylistListItemAdapterView adapterView, final
                                          PlaylistFragment context) {
    this.adapterView = adapterView;
    
  }
  
  @Override public SortedEntity getEntityFromList(int i) {
    return null;
  }
  
  @Override public int getItemCount() {
    return 0;
  }
  
  @Override public void getEntitiesInPlaylist(long id) {
  
  }
  
  @Override public void updateSortOrder(long id, int oldSortOrder, int newSortOrder) {
  
  }
  
  @Override public void removeEntity(long playlistID, long entityID, int sortOrder) {
  
  }
}
