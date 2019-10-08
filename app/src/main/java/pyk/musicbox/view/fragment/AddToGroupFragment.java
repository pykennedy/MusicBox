package pyk.musicbox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.AddToGroupFragmentPresenter;
import pyk.musicbox.utility.KeyboardManager;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.AddToGroupListItemAdapter;

public class AddToGroupFragment extends Fragment
    implements View.OnClickListener, SearchView.OnQueryTextListener {
  private long                        groupID;
  private TextView                    trackSlicer;
  private FloatingActionButton        fab;
  private AddToGroupListItemAdapter   adapter;
  private AddToGroupFragmentPresenter presenter;
  private Listener.FragmentListener   listener;
  private Bundle                      args;
  private String                      searchText;
  
  boolean[] slicerStatus = {false, false, true, false, false};
  
  public AddToGroupFragment() {
    setArguments(new Bundle());
  }
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    
    if (context instanceof Listener.FragmentListener) {
      listener = (Listener.FragmentListener) context;
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_addtogroup, container,
                                                      false);
    args = getArguments();
    searchText = (args != null) ? args.getString("searchText") : null;
    
    setHasOptionsMenu(true);
    
    trackSlicer = rootView.findViewById(R.id.tv_trackSlicer_fragmentAddToGroup);
    trackSlicer.setOnClickListener(this);
    
    fab = rootView.findViewById(R.id.fab_addButton_fragmentAddToGroup);
    fab.setOnClickListener(this);
    
    presenter = new AddToGroupFragmentPresenter();
    
    if (args != null) {
      groupID = args.getLong("id");
      listener.updateTitle(
          args.getString("groupName") != null ? "Adding to: " + args.getString("groupName")
                                              : "Error Retrieving Group Name");
    }
    
    adapter = new AddToGroupListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentAddToGroup);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    
    presenter.slicersChanged(adapter, slicerStatus);
    
    return rootView;
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_search, menu);
    super.onCreateOptionsMenu(menu, inflater);
    MenuItem   item       = menu.findItem(R.id.sv_menu);
    SearchView searchView = (SearchView) item.getActionView();
    searchView.setMaxWidth(Integer.MAX_VALUE);
    searchView.setOnQueryTextListener(this);
    if (!(searchText == null || searchText.equals(""))) {
      searchView.setQuery(searchText, true);
      searchView.setIconified(false);
    }
  }
  
  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.fab_addButton_fragmentAddToGroup:
        presenter.addToGroup((MainActivity) getActivity(), groupID, adapter.getSelectedTrackIDs());
        KeyboardManager.hideKeyboardFrom(getContext(), view);
        getActivity().onBackPressed();
        break;
      default:
        break;
    }
  }
  
  @Override public boolean onQueryTextSubmit(String s) {
    searchText = s;
    search();
    return false;
  }
  
  @Override public boolean onQueryTextChange(String s) {
    searchText = s;
    search();
    return false;
  }
  
  @Override
  public void onPause() {
    super.onPause();
    if (args != null) {
      args.putString("searchText", searchText);
    }
  }
  
  private void search() {
    if (searchText == null || searchText.equals("")) {
      adapter.applyFilters(slicerStatus);
    } else {
      adapter.search(slicerStatus, searchText);
    }
  }
}
