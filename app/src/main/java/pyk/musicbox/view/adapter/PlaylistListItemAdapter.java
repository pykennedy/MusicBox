package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.PlaylistListItemAdapterContract;
import pyk.musicbox.contract.fragment.PlaylistFragmentContract;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.presenter.PlaylistListItemAdapterPresenter;
import pyk.musicbox.view.fragment.PlaylistFragment;

public class PlaylistListItemAdapter
    extends SelectableAdapter<PlaylistListItemAdapter.ItemAdapterViewHolder>
    implements PlaylistFragmentContract.PlaylistListItemAdapterView, PlaylistListItemAdapterContract.PlaylistListItemAdapterView {
  PlaylistFragment fragment;
  PlaylistListItemAdapterPresenter presenter;
  long currentPlaylistID;
  
  public PlaylistListItemAdapter(PlaylistFragment fragment) {
    this.fragment = fragment;
    this.presenter = new PlaylistListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public PlaylistListItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                          int viewType) {
    return null;
  }
  
  @Override
  public void onBindViewHolder(@NonNull PlaylistListItemAdapter.ItemAdapterViewHolder holder,
                               int position) {
    
  }
  
  @Override public int getItemCount() {
    return 0;
  }
  
  @Override public void triggerRefresh() {
  
  }
  
  @Override public void getEntitiesInPlaylist(Long id) {
  
  }
  
  public void deleteItem(int position) {
  
  }
  
  static class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView                      title;
    TextView                      up;
    TextView                      down;
    long                          trackID;
    long                          groupID;
    int                           sortOrder;
    PlaylistListItemAdapterPresenter presenter;
    
    public ItemAdapterViewHolder(View itemView, final PlaylistListItemAdapterPresenter presenter) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
      up = itemView.findViewById(R.id.tv_up_groupList);
      down = itemView.findViewById(R.id.tv_down_groupList);
      title.setOnClickListener(this);
      up.setOnClickListener(this);
      down.setOnClickListener(this);
      
      this.presenter = presenter;
    }
    
    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.tv_up_groupList:
          if (sortOrder > 1 && sortOrder <= presenter.getItemCount()) {
            presenter.updateSortOrder(groupID, sortOrder, sortOrder - 1);
          }
          break;
        case R.id.tv_down_groupList:
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
