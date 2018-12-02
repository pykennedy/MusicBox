package pyk.musicbox.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.MainActivityContract;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.view.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView {
  
  //TODO: VVVVVVVVVVV
  /*
  make presenters for each fragment
  make each fragment present have a tiletapped method
  this method will take a MainActivityContract.MainActivityView object
  it will use that object to call a swap fragments method
  this method will be called by the tiletapped in each fragment presenter
   */
  
  private MainActivityPresenter mainActivityPresenter;
  private ViewPager pager;
  private PagerAdapter pagerAdapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainActivityPresenter = new MainActivityPresenter(this);
    
    pager = findViewById(R.id.vp_mainActivity);
    pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    pager.setAdapter(pagerAdapter);
    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
  
      @Override
      public void onPageSelected(int position) {}
  
      @Override
      public void onPageScrollStateChanged(int state) {}
    });
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }

  private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
  
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }
  
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new HomeFragment();
//          return new TrackFragment();
//          return new SettingsFragment();
//          return new SearchFragment();
//          return new SearchDetailsFragment();
//          return new GroupsFragment();
//          return new PlaylistsFragment();
        default:
          return new HomeFragment();
      }
    }
  
    @Override
    public int getCount() {
      return 1;
    }
  }
  
}
