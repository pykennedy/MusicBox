package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.presenter.GroupFragmentPresenter;
import pyk.musicbox.utility.SwipeDeleteCallback;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.GroupListItemAdapter;

public class GroupFragment extends Fragment implements View.OnClickListener {
  private TextView               title;
  private FloatingActionButton   fab;
  private GroupListItemAdapter   adapter;
  private String                 name;
  private Long                   id;
  private GroupFragmentPresenter presenter;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group, container, false);
    presenter = new GroupFragmentPresenter();
    
    title = rootView.findViewById(R.id.tv_title_fragmentGroup);
    fab = rootView.findViewById(R.id.fab_addButton_fragmentGroup);
    fab.setOnClickListener(this);
    
    adapter = new GroupListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentGroup);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeDeleteCallback(adapter));
    itemTouchHelper.attachToRecyclerView(recyclerView);
    
    name = getArguments().getString("groupName");
    id = getArguments().getLong("id");
    title.setText(name != null ? name : "Error Retrieving Group Name");
    
    presenter.getTracksInGroup(adapter, id);
    
    return rootView;
  }
  
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.fab_addButton_fragmentGroup:
        Bundle bundle = new Bundle();
        bundle.putString("groupName", name);
        bundle.putLong("id", id);
        AddToGroupFragment fragment = new AddToGroupFragment();
        fragment.setArguments(bundle);
        presenter.tileTapped((MainActivity) getActivity(), fragment, true);
        break;
      default:
        break;
    }
  }
}
