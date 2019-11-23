package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.ArtistListItemAdapterContract;
import pyk.musicbox.contract.fragment.ArtistFragmentContract;
import pyk.musicbox.model.entity.SortedEntity;
import pyk.musicbox.presenter.ArtistListItemAdapterPresenter;
import pyk.musicbox.view.fragment.ArtistFragment;

public class ArtistListItemAdapter
    extends RecyclerView.Adapter<ArtistListItemAdapter.ItemAdapterViewHolder>
    implements ArtistFragmentContract.ArtistListItemAdapterView, ArtistListItemAdapterContract.ArtistListItemAdapterView {
  ArtistFragment                 fragment;
  ArtistListItemAdapterPresenter presenter;
  long                           currentArtistID;
  
  public ArtistListItemAdapter(ArtistFragment fragment) {
    this.fragment = fragment;
    this.presenter = new ArtistListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_list, parent, false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(currentArtistID, presenter.getEntityFromList(position));
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  @Override public void getEntitiesInArtist(long id) {
    currentArtistID = id;
    presenter.getEntitiesInArist(id);
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView title;
    long     entityID;
    long     artistID;
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_artistList);
      icon = itemView.findViewById(R.id.iv_icon_artistList);
    }
    
    public void update(long artistID, SortedEntity entity) {
      String titleText = entity.getEntityName();
      this.title.setText(titleText);
      
      if(entity.getEntityType().equals("album")) {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_album_black_24dp));
      } else {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_track_black_24dp));
      }
    }
  }
}
