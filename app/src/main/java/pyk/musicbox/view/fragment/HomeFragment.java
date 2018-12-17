package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.contract.HomeFragmentContract;
import pyk.musicbox.presenter.HomeFragmentPresenter;
import pyk.musicbox.view.activity.MainActivity;

public class HomeFragment extends Fragment
    implements View.OnClickListener, HomeFragmentContract.HomeFragmentView {
  View                  groups;
  HomeFragmentPresenter homeFragmentPresenter;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    homeFragmentPresenter = new HomeFragmentPresenter(this);
    
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
    
    groups = rootView.findViewById(R.id.v_groups_fragmentHome);
    groups.setOnClickListener(this);
    
    return rootView;
  }
  
  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.v_groups_fragmentHome:
        homeFragmentPresenter.tileTapped((MainActivity) getActivity(),
                                         getResources().getString(R.string.groupsFragment), true);
        break;
      default:
        break;
    }
  }
}
