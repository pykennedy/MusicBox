package pyk.musicbox.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.contract.fragment.SearchFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.viewmodel.AnyEntityViewModel;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapterPresenter
    implements SearchListItemAdapterContract.SearchListItemAdapterPresenter, SearchFragmentContract.SearchListItemAdapterView {
  SearchListItemAdapterContract.SearchListItemAdapterView sliav;
  private AnyEntityViewModel                aevm;
  private MediatorLiveData<List<AnyEntity>> mediator = new MediatorLiveData<>();
  private LiveData<List<AnyEntity>> currentList;
  
  public SearchListItemAdapterPresenter(
      final SearchListItemAdapterContract.SearchListItemAdapterView sliav,
      final SearchFragment context) {
    this.sliav = sliav;
    aevm = ViewModelProviders.of(context).get(AnyEntityViewModel.class);
    
    mediator.observe(context, new Observer<List<AnyEntity>>() {
      @Override public void onChanged(@Nullable List<AnyEntity> anyEntities) {
        sliav.triggerRefresh();
      }
    });
  }
  
  @Override public AnyEntity getEntityFromList(int i) {
    List<AnyEntity> list = mediator.getValue();
    return (list != null) ? list.get(i) : null;
  }
  
  @Override public int getItemCount() {
    List<AnyEntity> list = mediator.getValue();
    return (list != null) ? list.size() : 0;
  }
  
  @Override public void applyFilters(final boolean[] slicers) {
    if(currentList != null) {
      mediator.removeSource(currentList);
    }
    
    currentList = aevm.getAllEntities(toTypesList(slicers));
    mediator.addSource(currentList, new Observer<List<AnyEntity>>() {
      @Override public void onChanged(@Nullable List<AnyEntity> allEntities) {
        mediator.setValue(allEntities);
      }
    });
  }
  
  private List<String> toTypesList(boolean[] slicers) {
    List<String> types = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (slicers[i]) {
        types.add(getTypeByIndex(i));
      }
    }
    
    return types;
  }
  
  private String getTypeByIndex(int i) {
    switch (i) {
      case 0:
        return "artist";
      case 1:
        return "album";
      case 2:
        return "track";
      case 3:
        return "group";
      case 4:
        return "playlist";
      default:
        return "";
    }
  }
}
