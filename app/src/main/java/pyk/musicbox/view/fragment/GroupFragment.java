package pyk.musicbox.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pyk.musicbox.R;
import pyk.musicbox.view.adapter.GroupListItemAdapter;

public class GroupFragment extends Fragment implements View.OnClickListener {
  private TextView title;
  private FloatingActionButton fab;
  private GroupListItemAdapter glia;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group, container, false);
    
    title = rootView.findViewById(R.id.tv_title_fragmentGroup);
    fab = rootView.findViewById(R.id.fab_addButton_fragmentGroup);
    fab.setOnClickListener(this);
  
    RecyclerView recyclerView = rootView.findViewById(R.id.rv_fragmentGroup);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(glia);
    
    String titleText = getArguments().getString("groupName");
    title.setText(titleText != null ? titleText : "Error Retrieving Group Name");
    
    return rootView;
  }
  
  
  @Override
  public void onClick(View view) {
  
  }
}
