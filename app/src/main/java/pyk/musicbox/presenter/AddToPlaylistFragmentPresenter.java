package pyk.musicbox.presenter;

import android.arch.lifecycle.ViewModelProviders;

import java.util.List;

import pyk.musicbox.contract.fragment.AddToPlaylistFragmentContract;
import pyk.musicbox.model.entity.AnyEntity;
import pyk.musicbox.model.entity.Playlist_GroupTrack;
import pyk.musicbox.model.viewmodel.Playlist_GroupTrackViewModel;
import pyk.musicbox.view.activity.MainActivity;

public class AddToPlaylistFragmentPresenter
    implements AddToPlaylistFragmentContract.AddToPlaylistFragmentPresenter {
  @Override public void slicersChanged(
      AddToPlaylistFragmentContract.AddToPlaylistListItemAdapterView adapterView,
      boolean[] slicers) {
    adapterView.applyFilters(slicers);
  }
  
  @Override
  public void addToPlaylist(MainActivity context, Long playlistID, List<AnyEntity> entities) {
    Playlist_GroupTrackViewModel playlistGroupTrackViewModel = ViewModelProviders.of(context).get(
        Playlist_GroupTrackViewModel.class);
    for (AnyEntity entity : entities) {
      playlistGroupTrackViewModel.insert(
          new Playlist_GroupTrack(playlistID, entity.getEntityID(), entity.getEntityType()));
    }
  }
}
