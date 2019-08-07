package pyk.musicbox.utility;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import pyk.musicbox.view.adapter.GroupListItemAdapter;

public class SwipeDeleteCallback extends ItemTouchHelper.SimpleCallback {
  GroupListItemAdapter adapter;
  private final ColorDrawable background = new ColorDrawable(Color.RED);
  ;
  
  public SwipeDeleteCallback(GroupListItemAdapter adapter) {
    super(0, ItemTouchHelper.END);
    this.adapter = adapter;
  }
  
  @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
    return false;
  }
  
  @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    adapter.deleteItem(viewHolder.getAdapterPosition());
  }
  
  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          float dX, float dY, int actionState, boolean isCurrentlyActive) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    View itemView = viewHolder.itemView;
    int backgroundCornerOffset = 0; // to adjust for rounded corners
  
    if (dX > 0) { // Swiping to the right
      background.setBounds(itemView.getLeft(), itemView.getTop(),
                           itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                           itemView.getBottom());
    
    } else if (dX < 0) { // Swiping to the left
      background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                           itemView.getTop(), itemView.getRight(), itemView.getBottom());
    } else { // view is unSwiped
      background.setBounds(0, 0, 0, 0);
    }
    background.draw(c);
  }
}
