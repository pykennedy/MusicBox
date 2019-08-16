package pyk.musicbox.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pyk.musicbox.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void defaultFragmentsExist() {
    onView(withId(R.id.rv_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.vp_mainActivity)).perform(swipeLeft());
    onView(withId(R.id.v_trackScroll_fragmentTrack)).check(matches(isDisplayed()));
    onView(withId(R.id.vp_mainActivity)).perform(swipeRight());
    onView(withId(R.id.rv_fragmentSearch)).check(matches(isDisplayed()));
  }
}