package pyk.musicbox.view.fragment;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.R;
import pyk.musicbox.support.StaticValues;
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
import static org.junit.Assert.fail;

public class SearchFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  //TODO: test long press to multi select after implement it
  
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
  public void createGroup() throws InterruptedException {
    onView(withId(R.id.fab_addButton_fragmentSearch)).perform(click());
    
    onView(withId(R.id.et_name_dialogAdd)).perform(typeText("aaa New Group"));
    
    onView(withText("Create Group")).perform(click());
  
    onView(withId(R.id.ab_fragmentGroup)).check(matches(isDisplayed()));
    
    pressBack();
  
    //TODO: create custom Matchers to check recyclerview items properly with onData
  }
  
  @Test
  public void clickGroupGoesToGroup() {
    onView(withId(R.id.rv_fragmentSearch))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("aaa Empty Group")), click()));
    onView(withId(R.id.ab_fragmentGroup)).check(matches(isDisplayed()));
  }
  
  @Test
  public void sliceForGroupsShowsOnlyGroups() {
    RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(
        R.id.rv_fragmentSearch);
    
    onView(withId(R.id.tv_groupSlicer_fragmentSearch))
        .perform(click());
    
    int count = recyclerView.getAdapter().getItemCount();
    
    if(count == StaticValues.totalGroups) {
      onView(withId(R.id.rv_fragmentSearch)).check(matches(hasDescendant(withText("aaa Empty Group"))));
    } else {
      fail("incorrect amount of expected items");
    }
  }
}
