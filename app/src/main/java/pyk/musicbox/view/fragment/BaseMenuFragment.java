package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class BaseMenuFragment extends BaseFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.basemenu_fragment, container, false);
    desiredTitle = "Music Box";
    
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.basemenu_frame, new SearchFragment());
    
    transaction.commit();
    
    return view;
  }
  
  public void swapFragment(Fragment fragment, FragmentManager fragmentManager) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.basemenu_frame, fragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }
}
