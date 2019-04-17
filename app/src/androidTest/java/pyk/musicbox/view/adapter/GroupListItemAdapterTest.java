package pyk.musicbox.view.adapter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pyk.musicbox.R;
import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class GroupListItemAdapterTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void itemsExist() {
    onView(withId(R.id.v_groups_fragmentHome)).perform(click());
    onView(withId(R.id.rv_fragmentGroups)).check(matches(hasDescendant(withText("GroupOld #2"))));
    onView(withId(R.id.rv_fragmentGroups)).perform(scrollToPosition(19));
    onView(withId(R.id.rv_fragmentGroups)).check(matches(hasDescendant(withText("GroupOld #19"))));
  }
}
