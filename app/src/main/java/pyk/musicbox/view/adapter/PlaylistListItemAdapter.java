package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.PlaylistListItemAdapterContract;
import pyk.musicbox.contract.fragment.PlaylistFragmentContract;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.presenter.PlaylistListItemAdapterPresenter;
import pyk.musicbox.view.fragment.PlaylistFragment;

public class PlaylistListItemAdapter
    extends SelectableAdapter<PlaylistListItemAdapter.ItemAdapterViewHolder>
    implements PlaylistFragmentContract.PlaylistListItemAdapterView, PlaylistListItemAdapterContract.PlaylistListItemAdapterView {
  PlaylistFragment                 fragment;
  PlaylistListItemAdapterPresenter presenter;
  long                             currentPlaylistID;
  
  public PlaylistListItemAdapter(PlaylistFragment fragment) {
    this.fragment = fragment;
    this.presenter = new PlaylistListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_list,
                                                                 parent, false);
    return new ItemAdapterViewHolder(view, presenter);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(currentPlaylistID, presenter.getEntityFromList(position), position);
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
  }
  
  @Override public void triggerRefresh() { notifyDataSetChanged(); }
  
  @Override public void getEntitiesInPlaylist(Long id) {
    currentPlaylistID = id;
    presenter.getEntitiesInPlaylist(id);
  }
  
  public void deleteItem(int position) {
    SortedEntity entity = presenter.getEntityFromList(position);
    presenter.removeEntity(currentPlaylistID, entity.getEntityID(), entity.getEntityType(),
                           entity.getSortOrder());
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {
    ImageView icon;
    TextView                         title;
    ImageView                         up;
    ImageView                         down;
    long                             entityID;
    long                             playlistID;
    int                              sortOrder;
    PlaylistListItemAdapterPresenter presenter;
    
    public ItemAdapterViewHolder(View itemView, final PlaylistListItemAdapterPresenter presenter) {
      super(itemView);
      icon = itemView.findViewById(R.id.iv_icon_playlistList);
      title = itemView.findViewById(R.id.tv_title_playlistList);
      up = itemView.findViewById(R.id.iv_up_playlistList);
      down = itemView.findViewById(R.id.iv_down_playlistList);
      title.setOnClickListener(this);
      up.setOnClickListener(this);
      down.setOnClickListener(this);
      
      this.presenter = presenter;
    }
    
    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.iv_up_playlistList:
          if (sortOrder > 1 && sortOrder <= presenter.getItemCount()) {
            presenter.updateSortOrder(playlistID, sortOrder, sortOrder - 1);
          }
          break;
        case R.id.iv_down_playlistList:
          if (sortOrder >= 1 && sortOrder < presenter.getItemCount()) {
            presenter.updateSortOrder(playlistID, sortOrder, sortOrder + 1);
          }
          break;
        default:
          break;
      }
    }
    
    public void update(long playlistID, SortedEntity entity, int position) {
      String titleText = entity.getEntityName();
      this.title.setText(titleText);
      this.entityID = entity.getEntityID();
      this.playlistID = playlistID;
      this.sortOrder = entity.getSortOrder();
      
      if(entity.getEntityType().equals("group")) {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_group_black_24dp));
      } else {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_track_black_24dp));
      }
    }
  }
}
