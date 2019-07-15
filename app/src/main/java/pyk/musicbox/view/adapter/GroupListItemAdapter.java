package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.contract.fragment.GroupFragmentContract;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.presenter.GroupListItemAdapterPresenter;
import pyk.musicbox.view.fragment.GroupFragment;
//TODO: set this thing up
public class GroupListItemAdapter
    extends SelectableAdapter<GroupListItemAdapter.ItemAdapterViewHolder>
    implements GroupFragmentContract.GroupListItemAdapterView, GroupListItemAdapterContract.GroupListItemAdapterView {
  GroupFragment fragment;
  GroupListItemAdapterPresenter presenter;
  
  public GroupListItemAdapter(GroupFragment fragment) {
    this.fragment = fragment;
    this.presenter = new GroupListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(presenter.getTrackFromList(position), position);
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
  }
  
  @Override public void getTracksInGroup(Long id) {
    presenter.getTracksInGroup(id);
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  static class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    Long     id;
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
  
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        
        }
      });
    }
    
    public void update(Track track, int position) {
      String titleText = track.getName();
      title.setText(titleText);
      id = track.getId();
    }
  }
}
