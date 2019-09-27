package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.adapter.PlaylistListItemAdapterContract;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.viewmodel.Playlist_GroupTrackViewModel;
import pyk.musicbox.view.fragment.PlaylistFragment;

public class PlaylistListItemAdapterPresenter implements PlaylistListItemAdapterContract.PlaylistListItemAdapterPresenter {
  private PlaylistListItemAdapterContract.PlaylistListItemAdapterView adapterView;
  private Playlist_GroupTrackViewModel pgtvm;
  private MediatorLiveData<List<SortedEntity>> mediator = new MediatorLiveData<>();
  private LiveData<List<SortedEntity>> currentList;
  
  public PlaylistListItemAdapterPresenter(final PlaylistListItemAdapterContract.PlaylistListItemAdapterView adapterView, final
                                          PlaylistFragment context) {
    this.adapterView = adapterView;
    pgtvm = ViewModelProviders.of(context).get(Playlist_GroupTrackViewModel.class);
    
    mediator.observe(context, new Observer<List<SortedEntity>>() {
      @Override public void onChanged(@Nullable List<SortedEntity> entities) {
        adapterView.triggerRefresh();
      }
    });
  }
  
  @Override public SortedEntity getEntityFromList(int i) {
    List<SortedEntity> list = mediator.getValue();
    return (list != null) ? list.get(i) : null;
  }
  
  @Override public int getItemCount() {
    List<SortedEntity> list = mediator.getValue();
    return (list != null) ? list.size() : 0;
  }
  
  @Override public void getEntitiesInPlaylist(long id) {
    if(currentList != null) {
      mediator.removeSource(currentList);
    }
    
    currentList = pgtvm.getItemsInPlaylist(id);
    mediator.addSource(currentList, new Observer<List<SortedEntity>>() {
      @Override public void onChanged(@Nullable List<SortedEntity> entities) {
        mediator.setValue(entities);
      }
    });
  }
  
  @Override public void updateSortOrder(long id, int oldSortOrder, int newSortOrder) {
    pgtvm.updatePlaylistGroupTrackSortOrder(id, oldSortOrder, newSortOrder);
  }
  
  @Override public void removeEntity(long playlistID, long entityID, String entityType, int sortOrder) {
    pgtvm.deleteGroupTrack(playlistID, entityID, entityType, sortOrder);
  }
}
