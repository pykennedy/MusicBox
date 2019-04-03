package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.fragment.HomeFragmentContract;
import pyk.musicbox.presenter.HomeFragmentPresenter;
import pyk.musicbox.view.activity.MainActivity;

public class HomeFragment extends Fragment
    implements View.OnClickListener, HomeFragmentContract.HomeFragmentView {
  View                  fromTheTop;
  View                  leftOff;
  View                  newSong;
  View                  settings;
  View                  search;
  View                  groups;
  View                  playlists;
  HomeFragmentPresenter homeFragmentPresenter;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    homeFragmentPresenter = new HomeFragmentPresenter(this);
    
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
  
    fromTheTop = rootView.findViewById(R.id.v_fromTheTop_fragmentHome);
    fromTheTop.setOnClickListener(this);
    leftOff = rootView.findViewById(R.id.v_leftOff_fragmentHome);
    leftOff.setOnClickListener(this);
    newSong = rootView.findViewById(R.id.v_newSong_fragmentHome);
    newSong.setOnClickListener(this);
    settings = rootView.findViewById(R.id.v_settings_fragmentHome);
    settings.setOnClickListener(this);
    search = rootView.findViewById(R.id.v_search_fragmentHome);
    search.setOnClickListener(this);
    groups = rootView.findViewById(R.id.v_groups_fragmentHome);
    groups.setOnClickListener(this);
    playlists = rootView.findViewById(R.id.v_playlists_fragmentHome);
    playlists.setOnClickListener(this);
    
    return rootView;
  }
  
  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.v_fromTheTop_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new TrackFragment(), false);
        break;
      case R.id.v_leftOff_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new TrackFragment(), false);
        break;
      case R.id.v_newSong_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new TrackFragment(), false);
        break;
      case R.id.v_settings_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new SettingsFragment(),
                                         true);
        break;
      case R.id.v_search_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new SearchFragment(), true);
        break;
      case R.id.v_groups_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new GroupsFragment(), true);
        break;
      case R.id.v_playlists_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(), new PlaylistsFragment(),
                                         true);
        break;
      default:
        break;
    }
  }
}
