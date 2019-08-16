package pyk.musicbox.matcher;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class BackgroundColorMatcher extends TypeSafeMatcher<View> {
  private final int color;
  
  public BackgroundColorMatcher(int color) {
    this.color = color;
  }
  
  @Override protected boolean matchesSafely(View item) {
    return ((ColorDrawable)(item).getBackground()).getColor() == this.color;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendText("with text color: ");
  }
}
