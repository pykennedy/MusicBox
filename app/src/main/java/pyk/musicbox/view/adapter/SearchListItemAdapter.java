package pyk.musicbox.view.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.SearchListItemAdapterContract;
import pyk.musicbox.contract.fragment.SearchFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.presenter.SearchListItemAdapterPresenter;
import pyk.musicbox.view.fragment.AlbumFragment;
import pyk.musicbox.view.fragment.ArtistFragment;
import pyk.musicbox.view.fragment.GroupFragment;
import pyk.musicbox.view.fragment.PlaylistFragment;
import pyk.musicbox.view.fragment.SearchFragment;

public class SearchListItemAdapter
    extends SelectableAdapter<SearchListItemAdapter.ItemAdapterViewHolder>
    implements SearchListItemAdapterContract.SearchListItemAdapterView
    , SearchFragmentContract.SearchListItemAdapterView {
  private SearchListItemAdapterPresenter sliap;
  private SearchFragment                 searchFragment;
  
  public SearchListItemAdapter(SearchFragment searchFragment) {
    super();
    this.searchFragment = searchFragment;
    this.sliap = new SearchListItemAdapterPresenter(this, searchFragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entity_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(sliap.getEntityFromList(position), position);
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
  
  @Override public void search(boolean[] slicers, String text) { sliap.search(slicers, text); }
  
  class ItemAdapterViewHolder extends ViewHolder {
    ImageView icon;
    TextView title;
    TextView other1;
    TextView other2;
    String   type;
    Long     id;
    View view;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      view = itemView;
      icon = itemView.findViewById(R.id.iv_icon_entityList);
      title = itemView.findViewById(R.id.tv_title_entityList);
      other1 = itemView.findViewById(R.id.tv_other1_entityList);
      other2 = itemView.findViewById(R.id.tv_other2_entityList);
      
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Bundle bundle = new Bundle();
          switch (type) {
            case "artist":
              bundle.putString("artistName", title.getText().toString());
              bundle.putLong("id", id);
              ArtistFragment artistFragment = new ArtistFragment();
              artistFragment.setArguments(bundle);
              searchFragment.swapFragment(artistFragment);
              break;
            case "album":
              bundle.putString("albumName", title.getText().toString());
              bundle.putLong("id", id);
              AlbumFragment albumFragment = new AlbumFragment();
              albumFragment.setArguments(bundle);
              searchFragment.swapFragment(albumFragment);
              break;
            case "track":
              searchFragment.swapTrack(id, title.getText().toString());
              break;
            case "group":
              bundle.putString("groupName", title.getText().toString());
              bundle.putLong("id", id);
              GroupFragment groupFragment = new GroupFragment();
              groupFragment.setArguments(bundle);
              searchFragment.swapFragment(groupFragment);
              break;
            case "playlist":
              bundle.putString("playlistName", title.getText().toString());
              bundle.putLong("id", id);
              PlaylistFragment playlistFragment = new PlaylistFragment();
              playlistFragment.setArguments(bundle);
              searchFragment.swapFragment(playlistFragment);
              break;
          }
        }
      });
    }
    
    void update(AnyEntity entity, int position) {
      String titleText = entity.getName();
      String other1Text = entity.getOtherInfo1();
      String other2Text = entity.getOtherInfo2();
      
      title.setText(titleText);
      other1.setText((other1Text == null) ? "" : other1Text);
      other2.setText((other2Text == null) ? "" : other2Text);
      type = entity.getEntityType();
      id = entity.getEntityID();
      
      switch (type) {
        case "artist":
          icon.setImageDrawable(searchFragment.getResources().getDrawable(R.drawable.ic_artist_black_24dp));
          break;
        case "album":
          icon.setImageDrawable(searchFragment.getResources().getDrawable(R.drawable.ic_album_black_24dp));
          break;
        case "track":
          icon.setImageDrawable(searchFragment.getResources().getDrawable(R.drawable.ic_track_black_24dp));
          break;
        case "group":
          icon.setImageDrawable(searchFragment.getResources().getDrawable(R.drawable.ic_group_black_24dp));
          break;
        case "playlist":
          icon.setImageDrawable(searchFragment.getResources().getDrawable(R.drawable.ic_playlist_black_24dp));
          break;
        default:
          break;
      }
      
      if (isSelected(position)) {
        view.setBackgroundColor(searchFragment.getResources().getColor(R.color.mcOrange50));
      } else {
        view.setBackgroundColor(searchFragment.getResources().getColor(R.color.mcWhite87));
      }
    }
  }
}
