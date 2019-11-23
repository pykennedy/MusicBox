package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.contract.fragment.GroupFragmentContract;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.presenter.GroupListItemAdapterPresenter;
import pyk.musicbox.view.fragment.GroupFragment;

public class GroupListItemAdapter
    extends SelectableAdapter<GroupListItemAdapter.ItemAdapterViewHolder>
    implements GroupFragmentContract.GroupListItemAdapterView, GroupListItemAdapterContract.GroupListItemAdapterView {
  GroupFragment                 fragment;
  GroupListItemAdapterPresenter presenter;
  long                          currentGroupID;
  
  public GroupListItemAdapter(GroupFragment fragment) {
    this.fragment = fragment;
    this.presenter = new GroupListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view, presenter);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(currentGroupID, presenter.getTrackFromList(position), position);
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
  }
  
  @Override public void getTracksInGroup(Long id) {
    currentGroupID = id;
    presenter.getTracksInGroup(id);
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  public void deleteItem(int position) {
    SortedTrack track = presenter.getTrackFromList(position);
    presenter.removeTrack(currentGroupID, track.getTrack().getId(), track.getSortOrder());
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView icon;
    TextView                      title;
    ImageView                     up;
    ImageView                      down;
    long                          trackID;
    long                          groupID;
    int                           sortOrder;
    GroupListItemAdapterPresenter presenter;
    
    public ItemAdapterViewHolder(View itemView, final GroupListItemAdapterPresenter presenter) {
      super(itemView);
      icon = itemView.findViewById(R.id.iv_icon_groupList);
      title = itemView.findViewById(R.id.tv_title_groupList);
      up = itemView.findViewById(R.id.iv_up_groupList);
      down = itemView.findViewById(R.id.iv_down_groupList);
      title.setOnClickListener(this);
      up.setOnClickListener(this);
      down.setOnClickListener(this);
      icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_track_black_24dp));
      
      this.presenter = presenter;
    }
  
    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.iv_up_groupList:
          if (sortOrder > 1 && sortOrder <= presenter.getItemCount()) {
            presenter.updateSortOrder(groupID, sortOrder, sortOrder - 1);
          }
          break;
        case R.id.iv_down_groupList:
          if (sortOrder >= 1 && sortOrder < presenter.getItemCount()) {
            presenter.updateSortOrder(groupID, sortOrder, sortOrder + 1);
          }
          break;
        default:
          break;
      }
    }
    
    public void update(long groupID, SortedTrack track, int position) {
      String titleText = track.getTrack().getName();
      title.setText(titleText);
      trackID = track.getTrack().getId();
      this.groupID = groupID;
      sortOrder = track.getSortOrder();
    }
  }
}
