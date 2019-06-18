package pyk.musicbox.view.fragment;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.view.activity.MainActivity;

public class HomeFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewsExist() {
    /*onView(withId(R.id.v_fromTheTop_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_fromTheTop_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_leftOff_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_leftOff_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_newSong_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_newSong_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_settings_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_settings_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_search_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_find_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_groups_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_groups_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_playlists_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_playlists_fragmentHome)).check(matches(isDisplayed()));*/
  }
}
