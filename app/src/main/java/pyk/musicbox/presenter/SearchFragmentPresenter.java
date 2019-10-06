package pyk.musicbox.presenter;

import android.arch.lifecycle.ViewModelProviders;

import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.contract.fragment.SearchFragmentContract;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.Playlist;
import pyk.musicbox.model.viewmodel.GroupViewModel;
import pyk.musicbox.model.viewmodel.PlaylistViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class SearchFragmentPresenter extends FragmentPresenter implements SearchFragmentContract.SearchFragmentPresenter {
  @Override public void slicersChanged(SearchFragmentContract.SearchListItemAdapterView sfsliap,
                                       boolean[] slicers) {
    sfsliap.applyFilters(slicers);
  }
  
  @Override
  public void addGroup(MainActivity context, String name, Callback.InsertGroupCB callback) {
    GroupViewModel groupViewModel = ViewModelProviders.of(context).get(GroupViewModel.class);
    groupViewModel.insert(new Group(name), callback);
  }
  
  @Override
  public void addPlaylist(MainActivity context, String name, Callback.InsertPlaylistCB callback) {
    PlaylistViewModel playlistViewModel = ViewModelProviders.of(context).get(PlaylistViewModel.class);
    playlistViewModel.insert(new Playlist(name), callback);
  }
  
  @Override
  public void search(SearchFragmentContract.SearchListItemAdapterView slia, boolean[] slicers,
                     String text) {
    slia.search(slicers, text);
  }
}
