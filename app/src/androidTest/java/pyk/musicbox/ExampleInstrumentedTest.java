package pyk.musicbox;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pyk.musicbox.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
    new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void viewExists() throws Exception {
    onView(withId(R.id.line1)).check(matches(isDisplayed()));
  }
}