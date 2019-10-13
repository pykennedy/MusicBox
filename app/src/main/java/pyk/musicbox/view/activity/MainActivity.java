package pyk.musicbox.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.contract.listener.Listener;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.view.fragment.BaseMenuFragment;
import pyk.musicbox.view.fragment.TrackFragment;
import pyk.musicbox.view.fragment.base.BaseFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView, Listener.FragmentListener {
  
  private MainActivityPresenter     mainActivityPresenter;
  private ViewPager                 pager;
  private FragmentStatePagerAdapter pagerAdapter;
  private TrackFragment             trackFragment;
  private BaseFragment              menuFragment;
  private Menu                      menu;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainActivityPresenter = new MainActivityPresenter(this);
    
    pager = findViewById(R.id.vp_mainActivity);
    pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    pager.setAdapter(pagerAdapter);
    pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        if (position == 1) {
          updateTitle(trackFragment.getTitle());
          menu.findItem(R.id.sv_menu).setVisible(false);
        } else {
          if (menuFragment == null) {
            updateTitle("Music Box");
          } else {
            updateTitle(menuFragment.desiredTitle);
          }
          menu.findItem(R.id.sv_menu).setVisible(true);
          ((SearchView)menu.findItem(R.id.sv_menu).getActionView()).setQuery("",false);
          ((SearchView)menu.findItem(R.id.sv_menu).getActionView()).setIconified(true);
        }
      }
    });
    
    getPerms();
    mainActivityPresenter.refreshTrackList(this);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    this.menu = menu;
    return true;
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }
  
  @Override public void swapFragment(BaseFragment fragment, boolean replace) {
    menuFragment = fragment;
    if (replace) {
      BaseMenuFragment baseMenuFragment = (BaseMenuFragment) pagerAdapter.getItem(0);
      baseMenuFragment.swapFragment(fragment, getSupportFragmentManager());
    } else {
      pager.setCurrentItem(1);
    }
  }
  
  @Override public void swapTrack(long id, String name) {
    trackFragment.updateInfo(true, id, name);
    pager.setCurrentItem(1);
  }
  
  @Override public void updateTitle(String newTitle) {
    setTitle(newTitle);
  }
  
  private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new BaseMenuFragment();
        case 1:
          trackFragment = (trackFragment == null) ? new TrackFragment() : trackFragment;
          return trackFragment;
        default:
          return new BaseMenuFragment();
      }
    }
    
    @Override
    public int getCount() {
      return 2;
    }
  }
  
  @Override public void onBackPressed() {
    if (pager.getCurrentItem() == 1) {
      pager.setCurrentItem(0);
    } else {
      super.onBackPressed();
    }
  }
  
  private void getPerms() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
           PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
           PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                                     Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        0);
    }
  }
}
