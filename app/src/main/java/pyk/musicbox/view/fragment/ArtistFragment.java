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
import pyk.musicbox.presenter.ArtistFragmentPresenter;
import pyk.musicbox.view.adapter.ArtistListItemAdapter;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class ArtistFragment extends BaseFragment {
  private ArtistListItemAdapter adapter;
  private String name;
  private long id;
  ArtistFragmentPresenter presenter;
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
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_artist, container, false);
    presenter = new ArtistFragmentPresenter();
    
    adapter = new ArtistListItemAdapter(this);
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentArtist);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    
    name = getArguments().getString("artistName");
    id = getArguments().getLong("id");
    desiredTitle = name != null ? name : "Error Retrieving Artist Name";
    listener.updateTitle(desiredTitle);
    
    presenter.getEntitiesInArist(adapter, id);
    
    return rootView;
  }
}
