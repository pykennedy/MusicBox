package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.contract.fragment.GroupFragmentContract;
import pyk.musicbox.view.fragment.GroupFragment;
//TODO: set this thing up
public class GroupListItemAdapter
    extends RecyclerView.Adapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements GroupFragmentContract.GroupListItemAdapterView {
  GroupFragment fragment;
  
  public GroupListItemAdapter(GroupFragment fragment) {
    this.fragment = fragment;
  }
  
  @NonNull @Override
  public SearchListItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                        int viewType) {
    return null;
  }
  
  @Override
  public void onBindViewHolder(@NonNull SearchListItemAdapter.ItemAdapterViewHolder holder,
                               int position) {
    
  }
  
  @Override public int getItemCount() {
    return 0;
  }
  
  @Override public void getTracksInGroup(Long id) {
  
  }
  
  static class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
    }
  }
}
