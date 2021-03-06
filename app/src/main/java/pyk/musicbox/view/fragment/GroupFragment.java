package pyk.musicbox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.GroupFragmentPresenter;
import pyk.musicbox.utility.GroupSwipeDeleteCallback;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.GroupListItemAdapter;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class GroupFragment extends BaseFragment implements View.OnClickListener {
  private FloatingActionButton   fab;
  private GroupListItemAdapter   adapter;
  private String                 name;
  private Long                   id;
  private GroupFragmentPresenter presenter;
  private Listener.FragmentListener listener;
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    
    if(context instanceof Listener.FragmentListener) {
      listener = (Listener.FragmentListener) context;
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group, container, false);
    presenter = new GroupFragmentPresenter();
    
    fab = rootView.findViewById(R.id.fab_addButton_fragmentGroup);
    fab.setOnClickListener(this);
    
    adapter = new GroupListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentGroup);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new GroupSwipeDeleteCallback(adapter));
    itemTouchHelper.attachToRecyclerView(recyclerView);
    
    name = getArguments().getString("groupName");
    id = getArguments().getLong("id");
    desiredTitle = name != null ? name : "Error Retrieving Group Name";
    listener.updateTitle(desiredTitle);
    
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
