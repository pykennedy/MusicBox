package pyk.musicbox.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
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
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.SearchFragmentPresenter;
import pyk.musicbox.utility.KeyboardManager;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.SearchListItemAdapter;

public class SearchFragment extends Fragment
    implements View.OnClickListener, AddDialogFragment.AddDialogListener, SearchView.OnQueryTextListener {
  private String               searchText;
  private TextView             artistSlicer;
  private TextView             albumSlicer;
  private TextView             trackSlicer;
  private TextView             groupSlicer;
  private TextView             playlistSlicer;
  private FloatingActionButton fab;
  private Bundle args;
  
  private int                     state; // 0 = browse, 1 = adding to group, 2 = adding to playlist
  private long                    modifyingID;
  private SearchListItemAdapter   slia;
  private SearchFragmentPresenter searchFragmentPresenter;
  
  private Listener.FragmentListener listener;
  
  // { artist , album , track , group , playlist }
  boolean[] slicerStatus = {true, true, true, true, true};
  
  //TODO: properly pass activity context to all fragments for better context management for room
  
  public SearchFragment() {
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
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
    args = getArguments();
    searchText = (args != null) ? args.getString("searchText") : null;
    
    //toolbar = rootView.findViewById(R.id.tb_fragmentSearch);
    //toolbar.inflateMenu(R.menu.menu_search);
    setHasOptionsMenu(true);
    artistSlicer = rootView.findViewById(R.id.tv_artistSlicer_fragmentSearch);
    artistSlicer.setOnClickListener(this);
    albumSlicer = rootView.findViewById(R.id.tv_albumSlicer_fragmentSearch);
    albumSlicer.setOnClickListener(this);
    trackSlicer = rootView.findViewById(R.id.tv_trackSlicer_fragmentSearch);
    trackSlicer.setOnClickListener(this);
    groupSlicer = rootView.findViewById(R.id.tv_groupSlicer_fragmentSearch);
    groupSlicer.setOnClickListener(this);
    playlistSlicer = rootView.findViewById(R.id.tv_playlistSlicer_fragmentSearch);
    playlistSlicer.setOnClickListener(this);
    fab = rootView.findViewById(R.id.fab_addButton_fragmentSearch);
    fab.setOnClickListener(this);
    
    searchFragmentPresenter = new SearchFragmentPresenter();
    
    slia = new SearchListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentSearch);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(slia);
  
    for (int i = 0; i < slicerStatus.length; i++) {
      setSlicerLight(i);
    }
    search();
    
    listener.updateTitle("Music Box");
    
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
  
  @Override
  public void onClick(View view) {
    if (state > 0) {
      return;
    }
    
    boolean update = true;
    
    switch (view.getId()) {
      case R.id.tv_artistSlicer_fragmentSearch:
        setSlicer(0);
        break;
      case R.id.tv_albumSlicer_fragmentSearch:
        setSlicer(1);
        break;
      case R.id.tv_trackSlicer_fragmentSearch:
        setSlicer(2);
        break;
      case R.id.tv_groupSlicer_fragmentSearch:
        setSlicer(3);
        break;
      case R.id.tv_playlistSlicer_fragmentSearch:
        setSlicer(4);
        break;
      case R.id.fab_addButton_fragmentSearch:
        showDialog();
        update = false;
        break;
      default:
        break;
    }
    
    if (update) {
      search();
    }
  }
  
  private void setSlicerLight(int i) {
    switch (i) {
      case 0:
        artistSlicer.setBackgroundColor(
            Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 1:
        albumSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 2:
        trackSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 3:
        groupSlicer.setBackgroundColor(Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      case 4:
        playlistSlicer.setBackgroundColor(
            Color.parseColor((slicerStatus[i]) ? "#ff0000" : "#ffffff"));
        break;
      default:
        break;
    }
  }
  
  private void forceSlicers() {
    for (int i = 0; i < 5; i++) {
      if (i == 2 && state > 0) {
        slicerStatus[i] = true;
        setSlicerLight(i);
      } else if (i == 3 && state == 2) {
        slicerStatus[i] = true;
        setSlicerLight(i);
      } else {
        slicerStatus[i] = false;
        setSlicerLight(i);
      }
    }
    search();
  }
  
  private void setSlicer(int index) {
    // if all slicers are on or if selection is not turned on, then turn all off except selection
    if (allSlicersAre(true) || !slicerStatus[index]) {
      for (int i = 0; i < 5; i++) {
        slicerStatus[i] = (i == index) ? true : false;
        setSlicerLight(i);
      }
    } else { // otherwise if selection is already on, turn on everything
      for (int i = 0; i < 5; i++) {
        slicerStatus[i] = true;
        setSlicerLight(i);
      }
    }
  }
  
  private boolean allSlicersAre(boolean onOrOff) {
    for (int i = 0; i < 5; i++) {
      if (slicerStatus[i] != onOrOff) {
        return false;
      }
    }
    return true;
  }
  
  private void showDialog() {
    DialogFragment dialog = new AddDialogFragment();
    dialog.setTargetFragment(SearchFragment.this, 300);
    dialog.show(getFragmentManager(), "AddDialogFragment");
  }
  
  public void swapFragment(Fragment fragment) {
    KeyboardManager.hideKeyboardFrom(getContext(), artistSlicer); // random view to make it work
    searchFragmentPresenter.tileTapped(
        (MainActivity) getActivity(), fragment, true);
  }
  
  @Override
  public void onGroupClick(DialogFragment dialog, final String name) {
    searchFragmentPresenter.addGroup((MainActivity) getActivity(), name,
                                     new Callback.InsertGroupCB() {
                                       @Override
                                       public void onResponse(boolean succeeded, String msg) {
                                         if (succeeded) {
                                           Bundle bundle = new Bundle();
                                           bundle.putString("groupName", name);
                                           bundle.putLong("id", Long.parseLong(msg));
                                           GroupFragment groupFragment = new GroupFragment();
                                           groupFragment.setArguments(bundle);
                                           swapFragment(groupFragment);
                                         } else {
                                           Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT)
                                                .show();
                                         }
                                       }
                                     });
  }
  
  @Override
  public void onPlaylistClick(DialogFragment dialog, final String name) {
    searchFragmentPresenter.addPlaylist((MainActivity) getActivity(), name,
                                        new Callback.InsertPlaylistCB() {
                                          @Override
                                          public void onResponse(boolean succeeded, String msg) {
                                            if (succeeded) {
                                              Bundle bundle = new Bundle();
                                              bundle.putString("playlistName", name);
                                              bundle.putLong("id", Long.parseLong(msg));
                                              PlaylistFragment playlistFragment =
                                                  new PlaylistFragment();
                                              playlistFragment.setArguments(bundle);
                                              swapFragment(playlistFragment);
                                            } else {
                                              Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT)
                                                   .show();
                                            }
                                          }
                                        });
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
    if(args != null) {
      args.putString("searchText", searchText);
    }
  }
  
  private void search() {
    if (searchText == null || searchText.equals("")) {
      slia.applyFilters(slicerStatus);
    } else {
      slia.search(slicerStatus, searchText);
    }
  }
}
