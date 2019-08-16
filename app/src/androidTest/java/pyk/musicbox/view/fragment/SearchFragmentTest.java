package pyk.musicbox.view.fragment;

import android.graphics.Color;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.R;
import pyk.musicbox.matcher.BackgroundColorMatcher;
import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class SearchFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewsExist() {
    onView(withId(R.id.ab_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_title_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_artistSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_albumSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_trackSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_groupSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_playlistSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.fab_addButton_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.rv_fragmentSearch)).check(matches(isDisplayed()));
  }
  
  @Test
  public void createGroup() {
    onView(withId(R.id.fab_addButton_fragmentSearch)).perform(click());
    onView(withId(R.id.et_name_dialogAdd)).perform(typeText("aaa New Group"));
    onView(withText("Create Group")).perform(click());
    
    onView(withId(R.id.ab_fragmentGroup)).check(matches(isDisplayed()));
    pressBack();
    
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("aaa New Group"))));
  }
  
  @Test
  public void clickGroupGoesToGroup() {
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.actionOnItem(hasDescendant(withText("aaa Empty Group")), click()));
    
    onView(withId(R.id.ab_fragmentGroup)).check(matches(isDisplayed()));
    
    onView(withId(R.id.tv_title_fragmentGroup)).check(matches(withText("aaa Empty Group")));
  }
  
  @Test
  public void sliceForGroupsShowsOnlyGroups() {
    RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(
        R.id.rv_fragmentSearch);
    
    onView(withId(R.id.tv_groupSlicer_fragmentSearch)).perform(click());
    
    // no artists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Taylor Swift")))));
    // no albums shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Red")))));
    // no tracks shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("State of Grace")))));
    //TODO: no playlists shown
    
    // a group is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("Brandenburg Concerto"))));
  }
  
  @Test
  public void slicersRemainAfterOnBackPressed() {
    onView(withId(R.id.tv_groupSlicer_fragmentSearch)).perform(click());
    
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.actionOnItem(hasDescendant(withText("aaa Empty Group")), click()));
    pressBack();
  
    onView(withId(R.id.tv_artistSlicer_fragmentSearch)).check(matches(new BackgroundColorMatcher(
        Color.parseColor("#FFFFFF"))));
    onView(withId(R.id.tv_albumSlicer_fragmentSearch)).check(matches(new BackgroundColorMatcher(
        Color.parseColor("#FFFFFF"))));
    onView(withId(R.id.tv_trackSlicer_fragmentSearch)).check(matches(new BackgroundColorMatcher(
        Color.parseColor("#FFFFFF"))));
    onView(withId(R.id.tv_groupSlicer_fragmentSearch)).check(matches(new BackgroundColorMatcher(
        Color.parseColor("#FF0000"))));
    onView(withId(R.id.tv_playlistSlicer_fragmentSearch)).check(matches(new BackgroundColorMatcher(
        Color.parseColor("#FFFFFF"))));
    
  }
}
