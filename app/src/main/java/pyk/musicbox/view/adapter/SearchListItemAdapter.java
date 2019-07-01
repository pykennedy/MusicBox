package pyk.musicbox.view.adapter;

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
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapter
    extends RecyclerView.Adapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements SearchListItemAdapterContract.SearchListItemAdapterView
    , SearchFragmentContract.SearchListItemAdapterView {
  private SearchListItemAdapterPresenter sliap;
  
  public SearchListItemAdapter(SearchFragment searchFragment) {
    super();
    this.sliap = new SearchListItemAdapterPresenter(this, searchFragment);
    
    // TODO: have count stored in shared preferences to detect if new tracks were added
    // TODO: if yes, spin and sleep for 2 seconds to buy time for list to populate
    
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
  
  static class ItemAdapterViewHolder extends ViewHolder {
    TextView title;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
    }
    
    void update(AnyEntity entity) {
      String titleText = entity.getName();
      
      title.setText(titleText);
    }
  }
}
