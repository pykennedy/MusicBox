package pyk.musicbox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.AlbumFragmentPresenter;
import pyk.musicbox.view.adapter.AlbumListItemAdapter;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class AlbumFragment extends BaseFragment {
  private AlbumListItemAdapter adapter;
  private String name;
  private long id;
  AlbumFragmentPresenter presenter;
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
    presenter = new AlbumFragmentPresenter();
    
    adapter = new AlbumListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentGroup);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    
    name = getArguments().getString("albumName");
    id = getArguments().getLong("id");
    desiredTitle = name != null ? name : "Error Retrieving Album Name";
    listener.updateTitle(desiredTitle);
    
    presenter.getTracksInAlbum(adapter, id);
    
    return rootView;
  }
}
