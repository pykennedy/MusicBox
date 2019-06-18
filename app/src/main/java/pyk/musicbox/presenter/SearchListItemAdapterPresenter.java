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
  
  public SearchListItemAdapterPresenter(
      final SearchListItemAdapterContract.SearchListItemAdapterView sliav, final SearchFragment context) {
    this.sliav = sliav;
    this.context = context;
    aevm = ViewModelProviders.of(context).get(AnyEntityViewModel.class);
    // TODO: try this https://medium.com/@BladeCoder/architecture-components-pitfalls-part-1-9300dd969808
  }
  
  @Override public AnyEntity getEntityFromList(int i) {
    return entities.get(i);
  }
  
  @Override public int getItemCount() {
    return entities.size();
  }
  
  @Override public void applyFilters(boolean[] slicers) {
    aevm.getAllEntities(toTypesList(slicers))
        .observe(context, new Observer<List<AnyEntity>>() {
          @Override public void onChanged(@Nullable List<AnyEntity> allEntities) {
            entities = allEntities;
            Log.e("asdf", ""+ allEntities.size());
            for(AnyEntity entity : allEntities) {
              Log.e("entity list", entity.getName());
            }
            sliav.triggerRefresh();
          }
        });
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
