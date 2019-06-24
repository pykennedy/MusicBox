package pyk.musicbox.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.util.Log;

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
  private AnyEntityViewModel aevm;
  private List<AnyEntity>    entities = new ArrayList<>();
  private SearchFragment     context;
  private Observer<List<AnyEntity>> observer;
  
  public SearchListItemAdapterPresenter(
      final SearchListItemAdapterContract.SearchListItemAdapterView sliav, final SearchFragment context) {
    this.sliav = sliav;
    this.context = context;
    aevm = ViewModelProviders.of(context).get(AnyEntityViewModel.class);
  }
  
  @Override public AnyEntity getEntityFromList(int i) {
    return entities.get(i);
  }
  
  @Override public int getItemCount() {
    return entities.size();
  }
  
  @Override public void applyFilters(final boolean[] slicers) {
    // TODO: figure out how to not have to make 349573489 observers, or use a mediator
    // TODO: try this if mediator is necessary: https://proandroiddev.com/mediatorlivedata-to-the-rescue-5d27645b9bc3
    if(observer != null) {
      aevm.getAllEntities(toTypesList(slicers)).removeObserver(observer);
    }
    observer = new Observer<List<AnyEntity>>() {
      @Override public void onChanged(@Nullable List<AnyEntity> allEntities) {
        Log.e("debugging", "live data onchange " + toTypesList(slicers).toString());
        entities = allEntities;
        Log.e("asdf", ""+ allEntities.size());
        for(AnyEntity entity : allEntities) {
          Log.e("entity list", entity.getName());
        }
        sliav.triggerRefresh();
      }
    };
    aevm.getAllEntities(toTypesList(slicers))
        .observe(context, observer);
  }
  
  public void setUpOvserver(final boolean[] slicers) {
    if(observer != null) {
      aevm.getAllEntities(toTypesList(slicers)).removeObserver(observer);
    }
    observer = new Observer<List<AnyEntity>>() {
      @Override public void onChanged(@Nullable List<AnyEntity> allEntities) {
        Log.e("debugging", "live data onchange " + toTypesList(slicers).toString());
        entities = allEntities;
        Log.e("asdf", ""+ allEntities.size());
        for(AnyEntity entity : allEntities) {
          Log.e("entity list", entity.getName());
        }
        sliav.triggerRefresh();
      }
    };
    aevm.getAllEntities(toTypesList(slicers))
        .observe(context, observer);
  }
  
  private List<String> toTypesList(boolean[] slicers) {
    List<String> types = new ArrayList<>();
    for(int i = 0; i < 5; i++) {
      if(slicers[i]) {
        types.add(getTypeByIndex(i));
      }
    }
    
    return types;
  }
  
  private String getTypeByIndex(int i) {
    switch(i) {
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
