package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;

public class BaseMenuFragment extends Fragment {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.basemenu_fragment, container, false);
    
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.basemenu_frame, new HomeFragment());
    
    transaction.commit();
    
    return view;
  }
  
}
