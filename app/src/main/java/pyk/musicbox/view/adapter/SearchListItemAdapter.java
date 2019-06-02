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
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.presenter.SearchListItemAdapterPresenter;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapter
    extends RecyclerView.Adapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements SearchListItemAdapterContract.SearchListItemAdapterView {
  private SearchListItemAdapterPresenter sliap;
  private SearchFragment                 searchFragment;
  
  public SearchListItemAdapter(SearchFragment searchFragment) {
    super();
    this.searchFragment = searchFragment;
    this.sliap = new SearchListItemAdapterPresenter(this, searchFragment);
  }
  
  @NonNull @Override
  public SearchListItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                        int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    return new SearchListItemAdapter.ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(sliap.getTrackFromList(position));
  }
  
  @Override public int getItemCount() {
    return sliap.getItemCount();
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  static class ItemAdapterViewHolder extends ViewHolder {
    TextView title;
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
    }
    
    void update(Track track) {
      String titleText = track.getName();
      
      title.setText(titleText);
    }
  }
}
