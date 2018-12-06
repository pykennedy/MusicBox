package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pyk.musicbox.R;

public class GroupsFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_groups, container, false);
    
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentGroups);
    
    return rootView;
  }
}
