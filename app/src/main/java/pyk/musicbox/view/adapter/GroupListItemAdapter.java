package pyk.musicbox.view.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.GroupListItemAdapterContract;
import pyk.musicbox.model.dbobjects.Group;
import pyk.musicbox.presenter.GroupListItemAdapterPresenter;

public class GroupListItemAdapter
    extends RecyclerView.Adapter<GroupListItemAdapter.ItemAdapterViewHolder>
    implements GroupListItemAdapterContract.GroupListItemAdapterView {
  GroupListItemAdapterPresenter presenter;
  
  public GroupListItemAdapter() {
    super();
    presenter = new GroupListItemAdapterPresenter(this);
    presenter.getGroupsFromDB();
    triggerRefresh();
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(presenter.getGroupFromList(position));
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
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
    
    void update(Group group) {
      String titleText = group.getGroupTitle();
      
      title.setText(titleText);
    }
  }
}