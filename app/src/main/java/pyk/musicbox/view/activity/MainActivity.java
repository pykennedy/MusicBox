package pyk.musicbox.view.activity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.activity.MainActivityContract;
import pyk.musicbox.model.entity.Track;
import pyk.musicbox.model.viewmodel.TrackViewModel;
import pyk.musicbox.presenter.MainActivityPresenter;
import pyk.musicbox.view.fragment.BaseMenuFragment;
import pyk.musicbox.view.fragment.TrackFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView {
  
  private MainActivityPresenter     mainActivityPresenter;
  private ViewPager                 pager;
  private FragmentStatePagerAdapter pagerAdapter;
  
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
    
    getPerms();
    //mainActivityPresenter.refreshTrackList(this);
    refreshTrackList();
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }
  
  @Override public void swapFragment(Fragment fragment, boolean replace) {
    if (replace) {
      BaseMenuFragment baseMenuFragment = (BaseMenuFragment) pagerAdapter.getItem(0);
      baseMenuFragment.swapFragment(fragment, getSupportFragmentManager());
    } else {
      pager.setCurrentItem(1);
    }
  

  }
  
  public void refreshTrackList() {
    TrackViewModel trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
    Uri            uri            = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    Cursor         cursor         = this.getContentResolver().query(uri, null, null, null, null);
    
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        do {
          Track track = new Track(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                                  cursor.getString(
                                      cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
          //tracks.add(track);
          trackViewModel.insert(track);
          Log.e("asdf", track.getTrackName());
        } while (cursor.moveToNext());
      }
      cursor.close();
    }
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
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        0);
    }
  }
}
