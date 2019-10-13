package pyk.musicbox.view.adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.contract.fragment.SearchFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.presenter.SearchListItemAdapterPresenter;
import pyk.musicbox.view.fragment.GroupFragment;
import pyk.musicbox.view.fragment.PlaylistFragment;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapter
    extends SelectableAdapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements SearchListItemAdapterContract.SearchListItemAdapterView
    , SearchFragmentContract.SearchListItemAdapterView {
  private SearchListItemAdapterPresenter sliap;
  private SearchFragment                 searchFragment;
  
  public SearchListItemAdapter(SearchFragment searchFragment) {
    super();
    this.searchFragment = searchFragment;
    this.sliap = new SearchListItemAdapterPresenter(this, searchFragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entity_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(sliap.getEntityFromList(position), position);
  }
  
  @Override public int getItemCount() {
    return sliap.getItemCount();
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  @Override public void applyFilters(boolean[] slicers) {
    sliap.applyFilters(slicers);
  }
  
  @Override public void search(boolean[] slicers, String text) { sliap.search(slicers, text); }
  
  class ItemAdapterViewHolder extends ViewHolder {
    TextView title;
    String   type;
    Long     id;
    
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_entityList);
      
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Bundle bundle = new Bundle();
          switch (type) {
            case "artist":
              break;
            case "album":
              break;
            case "track":
              searchFragment.swapTrack(id, title.getText().toString());
              break;
            case "group":
              bundle.putString("groupName", title.getText().toString());
              bundle.putLong("id", id);
              GroupFragment groupFragment = new GroupFragment();
              groupFragment.setArguments(bundle);
              searchFragment.swapFragment(groupFragment);
              break;
            case "playlist":
              bundle.putString("playlistName", title.getText().toString());
              bundle.putLong("id", id);
              PlaylistFragment playlistFragment = new PlaylistFragment();
              playlistFragment.setArguments(bundle);
              searchFragment.swapFragment(playlistFragment);
              break;
          }
        }
      });
    }
    
    void update(AnyEntity entity, int position) {
      String titleText = entity.getName();
      title.setText(titleText);
      type = entity.getEntityType();
      id = entity.getEntityID();
      
      if (isSelected(position)) {
        title.setTextColor(Color.RED);
      } else {
        title.setTextColor(Color.BLACK);
      }
    }
  }
}
