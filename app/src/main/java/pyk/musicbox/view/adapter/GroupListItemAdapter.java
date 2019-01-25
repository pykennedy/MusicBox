package pyk.musicbox.view.adapter;


import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import pyk.musicbox.contract.GroupListItemAdapterContract;

public class GroupListItemAdapter implements GroupListItemAdapterContract.GroupListItemAdapterView {
  
  @Override public void triggerRefresh() {
  
  }
  
  static class ItemAdapterViewHolder extends ViewHolder {
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
    }
    
  }
}
