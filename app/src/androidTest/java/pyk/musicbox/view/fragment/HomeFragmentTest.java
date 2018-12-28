package pyk.musicbox.view.fragment;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.R;
import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class HomeFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewsExist() {
    onView(withId(R.id.v_fromTheTop_fragmentHome)).check(matches(isDisplayed()));
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
    onView(withId(R.id.tv_playlists_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void fromTheTopClick() {
    onView(withId(R.id.v_fromTheTop_fragmentHome)).perform(click());
    onView(withId(R.id.v_trackScroll_fragmentTrack)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_fromTheTop_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void leftOffClick() {
    onView(withId(R.id.v_leftOff_fragmentHome)).perform(click());
    onView(withId(R.id.v_trackScroll_fragmentTrack)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_leftOff_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void newSongClick() {
    onView(withId(R.id.v_newSong_fragmentHome)).perform(click());
    onView(withId(R.id.v_trackScroll_fragmentTrack)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_newSong_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void settingsClick() {
    onView(withId(R.id.v_settings_fragmentHome)).perform(click());
    onView(withId(R.id.tv_placeholder_fragmentSettings)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_settings_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void searchClick() {
    onView(withId(R.id.v_search_fragmentHome)).perform(click());
    onView(withId(R.id.rv_fragmentSearch)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_search_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void groupsClick() {
    onView(withId(R.id.v_groups_fragmentHome)).perform(click());
    onView(withId(R.id.rv_fragmentGroups)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_groups_fragmentHome)).check(matches(isDisplayed()));
  }
  
  @Test
  public void playlistsClick() {
    onView(withId(R.id.v_playlists_fragmentHome)).perform(click());
    onView(withId(R.id.rv_fragmentPlaylists)).check(matches(isDisplayed()));
    onView(isRoot()).perform(pressBack());
    onView(withId(R.id.v_playlists_fragmentHome)).check(matches(isDisplayed()));
  }
}
