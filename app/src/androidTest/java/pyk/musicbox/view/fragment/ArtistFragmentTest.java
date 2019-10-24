package pyk.musicbox.view.fragment;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pyk.musicbox.R;
import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ArtistFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewExist() {
    onView(withId(R.id.rv_fragmentSearch))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("Taylor Swift")), click()));
    
    onView(withId(R.id.rv_fragmentArtist)).check(matches(isDisplayed()));
  }
}
