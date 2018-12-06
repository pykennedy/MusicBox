package pyk.musicbox.view.fragment;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.R;
import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class HomeFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewsExist() throws Exception {
    onView(withId(R.id.v_groups_fragmentHome)).check(matches(isDisplayed()));
    onView(withId(R.id.v_groups_fragmentHome)).perform(click());
    onView(withId(R.id.rv_fragmentGroups)).check(matches(isDisplayed()));
  }
}
