package pyk.musicbox.presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.model.entity.AllEntities;
import pyk.musicbox.model.viewmodel.AllEntitiesViewModel;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapterPresenter
    implements SearchListItemAdapterContract.SearchListItemAdapterPresenter {
  SearchListItemAdapterContract.SearchListItemAdapterView sliav;
  private AllEntitiesViewModel aevm;
  private List<AllEntities> entities = new ArrayList<>();
  
  public SearchListItemAdapterPresenter(
      final SearchListItemAdapterContract.SearchListItemAdapterView sliav, SearchFragment context) {
    this.sliav = sliav;
    
    aevm = ViewModelProviders.of(context).get(AllEntitiesViewModel.class);
    List<String> entityTypes = Arrays.asList("track", "album", "artist", "group", "playlist");
    aevm.getAllEntities(entityTypes)
        .observe(context, new Observer<List<AllEntities>>() {
          @Override public void onChanged(@Nullable List<AllEntities> allEntities) {
            entities = allEntities;
            Log.e("asdf", ""+ allEntities.size());
            for(AllEntities entity : allEntities) {
              Log.e("entity list", entity.getName());
            }
            sliav.triggerRefresh();
          }
        });
  }
  
  @Override public AllEntities getEntityFromList(int i) {
    return entities.get(i);
  }
  
  @Override public int getItemCount() {
    return entities.size();
  }
}
