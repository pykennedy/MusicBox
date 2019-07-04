package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GroupListItemAdapter extends RecyclerView.Adapter<SearchListItemAdapter.ItemAdapterViewHolder> {
  
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
  
  static class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
  
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
    }
  }
}
