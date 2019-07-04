package pyk.musicbox.view.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapter
    extends RecyclerView.Adapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements SearchListItemAdapterContract.SearchListItemAdapterView
    , SearchFragmentContract.SearchListItemAdapterView {
  private SearchListItemAdapterPresenter sliap;
  private SearchFragment searchFragment;
  
  public SearchListItemAdapter(SearchFragment searchFragment) {
    super();
    this.searchFragment = searchFragment;
    this.sliap = new SearchListItemAdapterPresenter(this, searchFragment);
    
    sliap.applyFilters(new boolean[]{true, true, true, true, true});
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(sliap.getEntityFromList(position));
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
  
  class ItemAdapterViewHolder extends ViewHolder {
    TextView title;
    String type;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
      
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          switch (type) {
            case "artist":
              break;
            case "album":
              break;
            case "track":
              break;
            case "group":
              Bundle bundle = new Bundle();
              bundle.putString("groupName", title.getText().toString());
              GroupFragment groupFragment = new GroupFragment();
              groupFragment.setArguments(bundle);
              searchFragment.swapFragment(groupFragment);
              break;
            case "playlist":
              break;
          }
        }
      });
    }
    
    void update(AnyEntity entity) {
      String titleText = entity.getName();
      title.setText(titleText);
      type = entity.getEntityType();
    }
  }
}
