package pyk.musicbox.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.MainActivityContract;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.view.fragment.BaseMenuFragment;
import pyk.musicbox.view.fragment.TrackFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView {
  
  //TODO: VVVVVVVVVVV
  /*
  clean up the mess of these fragment transitions
   */
  
  private MainActivityPresenter mainActivityPresenter;
  private ViewPager             pager;
  private FragmentStatePagerAdapter  pagerAdapter;
  
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
  
  @Override public void swapFragment(String fragment, boolean replace) {
    BaseMenuFragment baseMenuFragment = (BaseMenuFragment) pagerAdapter.getItem(0);
    baseMenuFragment.swapFragment(fragment, getSupportFragmentManager());
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
          return new TrackFragment();
        default:
          return new BaseMenuFragment();
      }
    }
  
    @Override
    public int getCount() {
      return 1;
    }
  }
  
}
