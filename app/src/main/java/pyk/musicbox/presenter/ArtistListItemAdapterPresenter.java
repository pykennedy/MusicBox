package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.List;

import pyk.musicbox.contract.adapter.ArtistListItemAdapterContract;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.model.viewmodel.Artist_AlbumTrackViewModel;
import pyk.musicbox.view.fragment.ArtistFragment;

public class ArtistListItemAdapterPresenter implements ArtistListItemAdapterContract.ArtistListItemAdapterPresenter {
  private ArtistListItemAdapterContract.ArtistListItemAdapterView adapterView;
  private Artist_AlbumTrackViewModel aatvm;
  private MediatorLiveData<List<SortedEntity>> mediator = new MediatorLiveData<>();
  private LiveData<List<SortedEntity>> currentList;
  
  public ArtistListItemAdapterPresenter(final ArtistListItemAdapterContract.ArtistListItemAdapterView adapterView, final
                                        ArtistFragment context) {
    this.adapterView = adapterView;
    aatvm = ViewModelProviders.of(context).get(Artist_AlbumTrackViewModel.class);
  
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
  
  @Override public void getEntitiesInArist(long id) {
    if(currentList != null) {
      mediator.removeSource(currentList);
    }
  
    currentList = aatvm.getItemsInArtist(id);
    mediator.addSource(currentList, new Observer<List<SortedEntity>>() {
      @Override public void onChanged(@Nullable List<SortedEntity> entities) {
        mediator.setValue(entities);
      }
    });
  }
}
