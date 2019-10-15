package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.AlbumListItemAdapterContract;
import pyk.musicbox.contract.fragment.AlbumFragmentContract;
import pyk.musicbox.model.entity.SortedTrack;
import pyk.musicbox.presenter.AlbumListItemAdapterPresenter;
import pyk.musicbox.view.fragment.AlbumFragment;

public class AlbumListItemAdapter
    extends RecyclerView.Adapter<AlbumListItemAdapter.ItemAdapterViewHolder>
    implements AlbumFragmentContract.AlbumListItemAdapterView, AlbumListItemAdapterContract.AlbumListItemAdapterView {
  AlbumFragment fragment;
  AlbumListItemAdapterPresenter presenter;
  long currentAlbumID;
  
  public AlbumListItemAdapter(AlbumFragment fragment) {
    this.fragment = fragment;
    this.presenter = new AlbumListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public AlbumListItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                       int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_list, parent, false);
    
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull AlbumListItemAdapter.ItemAdapterViewHolder holder,
                                         int position) {
    holder.update(currentAlbumID, presenter.getTrackFromList(position));
  }
  
  @Override public int getItemCount() { return presenter.getItemCount(); }
  
  @Override public void triggerRefresh() { notifyDataSetChanged(); }
  
  @Override public void getTracksInAlbum(long id) {
    currentAlbumID = id;
    presenter.getTracksInAlbum(id);
  }
  
  static class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    long trackID;
    long albumID;
    
    public ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_albumList);
    }
    
    public void update(long albumID, SortedTrack track) {
      String titleText = track.getTrack().getName();
      title.setText(titleText);
      trackID = track.getTrack().getId();
      this.albumID = albumID;
    }
  }
}
