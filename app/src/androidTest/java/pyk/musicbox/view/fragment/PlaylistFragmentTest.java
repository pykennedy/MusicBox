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
import static org.hamcrest.Matchers.allOf;

public class PlaylistFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewExist() {
    onView(withId(R.id.rv_fragmentSearch))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("aaa Empty Playlist")), click()));
    
    onView(withId(R.id.rv_fragmentPlaylist)).check(matches(isDisplayed()));
    onView(withId(R.id.fab_addButton_fragmentPlaylist)).check(matches(isDisplayed()));
  }
  
  @Test
  public void addToPlaylist() {
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.actionOnItem(hasDescendant(withText("aaa Empty Playlist")), click()));
    
    onView(withId(R.id.fab_addButton_fragmentPlaylist)).perform(click());
    
    onView(withId(R.id.rv_fragmentAddToPlaylist))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("...Ready for It?")), click()));
    onView(withId(R.id.rv_fragmentAddToPlaylist))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("Delicate")), click()));
    onView(withId(R.id.rv_fragmentAddToPlaylist))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("Don't Blame Me")), click()));
    onView(withId(R.id.rv_fragmentAddToPlaylist))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("End Game")), click()));
    onView(withId(R.id.rv_fragmentAddToPlaylist))
        .perform(RecyclerViewActions.actionOnItem(
            hasDescendant(withText("aaa Empty Group")), click()));
    onView(withId(R.id.fab_addButton_fragmentAddToPlaylist)).perform(click());
    
    onView(withId(R.id.rv_fragmentPlaylist)).check(
        matches(hasDescendant(withText("...Ready for It?"))));
    onView(withId(R.id.rv_fragmentPlaylist)).check(matches(hasDescendant(withText("Delicate"))));
    onView(withId(R.id.rv_fragmentPlaylist)).check(matches(hasDescendant(withText("Don't Blame Me"))));
    onView(withId(R.id.rv_fragmentPlaylist)).check(matches(hasDescendant(withText("End Game"))));
    onView(withId(R.id.rv_fragmentPlaylist)).check(
        matches(hasDescendant(withText("aaa Empty Group"))));
  }
}
