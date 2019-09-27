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
import pyk.musicbox.contract.adapter.AddToPlaylistListItemAdapterContract;
import pyk.musicbox.contract.fragment.AddToPlaylistFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.presenter.AddToPlaylistListItemAdapterPresenter;
import pyk.musicbox.view.fragment.AddToPlaylistFragment;

public class AddToPlaylistListItemAdapter
    extends SelectableAdapter<AddToPlaylistListItemAdapter.ItemAdapterViewHolder>
    implements AddToPlaylistListItemAdapterContract.AddToPlaylistListItemAdapterView
    , AddToPlaylistFragmentContract.AddToPlaylistListItemAdapterView {
  AddToPlaylistFragment                 fragment;
  AddToPlaylistListItemAdapterPresenter presenter;
  LongSparseArray<AnyEntity>            selectedEntities = new LongSparseArray<>();
  
  public AddToPlaylistListItemAdapter(AddToPlaylistFragment fragment) {
    super();
    this.fragment = fragment;
    this.presenter = new AddToPlaylistListItemAdapterPresenter(this, fragment);
  }
  
  @NonNull @Override public AddToPlaylistListItemAdapter.ItemAdapterViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entity_list, parent,
                                                                 false);
    return new ItemAdapterViewHolder(view);
  }
  
  @Override
  public void onBindViewHolder(@NonNull AddToPlaylistListItemAdapter.ItemAdapterViewHolder holder,
                               int position) {
    holder.update(presenter.getEntityFromList(position), position);
  }
  
  @Override public int getItemCount() { return presenter.getItemCount(); }
  
  @Override public void triggerRefresh() { notifyDataSetChanged(); }
  
  @Override public void applyFilters(boolean[] slicers) { presenter.applyFilters(slicers); }
  
  public List<AnyEntity> getSelectedEntityIDs() {
    List<AnyEntity> entities = new ArrayList<>();
    for(int i = 0; i < selectedEntities.size(); i++) {
      entities.add(selectedEntities.valueAt(i));
    }
    return entities;
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    String   type;
    long     key;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title_entityList);
      
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
      key = (type.equals("group")) ? entity.getEntityID() * -1 : entity.getEntityID();
      
      if (isSelected(position)) {
        title.setTextColor(Color.RED);
        selectedEntities.append(key, entity);
      } else {
        title.setTextColor(Color.BLACK);
        selectedEntities.remove(key);
      }
    }
  }
}
