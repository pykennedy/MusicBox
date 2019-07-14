package pyk.musicbox.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pyk.musicbox.R;
import pyk.musicbox.contract.adapter.AddToGroupListItemAdapterContract;
import pyk.musicbox.contract.fragment.AddToGroupFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.presenter.AddToGroupListItemAdapterPresenter;
import pyk.musicbox.view.fragment.AddToGroupFragment;

public class AddToGroupListItemAdapter
    extends SelectableAdapter<AddToGroupListItemAdapter.ItemAdapterViewHolder>
    implements AddToGroupListItemAdapterContract.AddToGroupListItemAdapterView
    , AddToGroupFragmentContract.AddToGroupListItemAdapterView {
  AddToGroupFragment                 fragment;
  AddToGroupListItemAdapterPresenter presenter;
  LongSparseArray<AnyEntity> selectedTracks = new LongSparseArray<>();
  
  public AddToGroupListItemAdapter(AddToGroupFragment fragment) {
    super();
    this.fragment = fragment;
    this.presenter = new AddToGroupListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override
  public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {
    holder.update(presenter.getEntityFromList(position), position);
  }
  
  @Override public int getItemCount() {
    return presenter.getItemCount();
  }
  
  @Override public void triggerRefresh() {
    notifyDataSetChanged();
  }
  
  
  @Override public void applyFilters(boolean[] slicers) {
    presenter.applyFilters(slicers);
  }
  
  public List<Long> getSelectedTrackIDs() {
    List<Long> tracks = new ArrayList<>();
    for(int i = 0; i < selectedTracks.size(); i++) {
      tracks.add(selectedTracks.valueAt(i).getEntityID());
    }
    return tracks;
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    String   type;
    Long     id;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_groupList);
      
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          toggleSelection(getAdapterPosition());
        }
      });
    }
    
    void update(AnyEntity entity, int position) {
      String titleText = entity.getName();
      title.setText(titleText);
      type = entity.getEntityType();
      id = entity.getEntityID();
      
      
      if (isSelected(position)) {
        title.setTextColor(Color.RED);
        selectedTracks.append(id, entity);
      } else {
        title.setTextColor(Color.BLACK);
        selectedTracks.remove(id);
      }
    }
  }
}
