package pyk.musicbox.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
  
  @Override public void search(boolean[] slicers, String text) { presenter.search(slicers, text); }
  
  public List<AnyEntity> getSelectedEntityIDs() {
    List<AnyEntity> entities = new ArrayList<>();
    for(int i = 0; i < selectedEntities.size(); i++) {
      entities.add(selectedEntities.valueAt(i));
    }
    return entities;
  }
  
  class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
    View view;
    ImageView icon;
    TextView title;
    TextView other1;
    TextView other2;
    String   type;
    long     key;
    
    ItemAdapterViewHolder(View itemView) {
      super(itemView);
      view = itemView;
      title = itemView.findViewById(R.id.tv_title_entityList);
      icon = itemView.findViewById(R.id.iv_icon_entityList);
      other1 = itemView.findViewById(R.id.tv_other1_entityList);
      other2 = itemView.findViewById(R.id.tv_other2_entityList);
      
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          toggleSelection(getAdapterPosition());
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
      key = (type.equals("group")) ? entity.getEntityID() * -1 : entity.getEntityID();
  
      if(entity.getEntityType().equals("group")) {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_group_black_24dp));
      } else {
        icon.setImageDrawable(fragment.getResources().getDrawable(R.drawable.ic_track_black_24dp));
      }
  
      if (isSelected(position)) {
        view.setBackgroundColor(fragment.getResources().getColor(R.color.mcOrange50));
        selectedEntities.append(key, entity);
      } else {
        view.setBackgroundColor(fragment.getResources().getColor(R.color.mcWhite87));
        selectedEntities.remove(key);
      }
    }
  }
}
