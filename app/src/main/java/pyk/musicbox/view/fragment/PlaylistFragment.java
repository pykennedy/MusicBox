package pyk.musicbox.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.Group_TrackViewModel;
import pyk.musicbox.presenter.PlaylistFragmentPresenter;
import pyk.musicbox.utility.PlaylistSwipeDeleteCallback;
import pyk.musicbox.view.activity.MainActivity;
import pyk.musicbox.view.adapter.PlaylistListItemAdapter;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class PlaylistFragment extends BaseFragment implements View.OnClickListener {
  private FloatingActionButton fab;
  private PlaylistListItemAdapter adapter;
  private String name;
  private long id;
  private PlaylistFragmentPresenter presenter;
  private Listener.FragmentListener listener;
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    
    if(context instanceof Listener.FragmentListener) {
      listener = (Listener.FragmentListener) context;
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_playlist, container, false);
    presenter = new PlaylistFragmentPresenter();
    
    fab = rootView.findViewById(R.id.fab_addButton_fragmentPlaylist);
    fab.setOnClickListener(this);
    
    adapter = new PlaylistListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentPlaylist);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new PlaylistSwipeDeleteCallback(adapter));
    itemTouchHelper.attachToRecyclerView(recyclerView);
  
    name = getArguments().getString("playlistName");
    id = getArguments().getLong("id");
    desiredTitle = name != null ? name : "Error Retrieving Playlist Name";
    listener.updateTitle(desiredTitle);
  
    presenter.getEntitiesInPlaylist(adapter, id);
    
    return rootView;
  }
  
  public void swapTrack(final long id, String entityType, final String name, final long playlistID) {
    if(entityType.equals("group")) {
      new Thread(new Runnable() {
        public void run() {
          Group_TrackViewModel gtvm = ViewModelProviders.of(getActivity()).get(Group_TrackViewModel.class);
          gtvm.getFirstTrack(id, new Callback.FirstTrackCB() {
            @Override public void onComplete(boolean succeeded, final Track track) {
              getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    presenter.swapTrack((MainActivity) getActivity(), track.getId(), track.getName(), playlistID, true);
                }
              });
            }
          });
        }
      }).start();
    } else {
      presenter.swapTrack((MainActivity) getActivity(), id, name, playlistID, false);
    }
  }
  
  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.fab_addButton_fragmentPlaylist:
        Bundle bundle = new Bundle();
        bundle.putString("playlistName", name);
        bundle.putLong("id", id);
        AddToPlaylistFragment fragment = new AddToPlaylistFragment();
        fragment.setArguments(bundle);
        Log.e("asdf", "gothere");
        presenter.tileTapped((MainActivity) getActivity(), fragment, true);
        break;
      default:
        break;
    }
  }
}
