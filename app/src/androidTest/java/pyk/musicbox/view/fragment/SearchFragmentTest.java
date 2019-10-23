package pyk.musicbox.view.fragment;

import android.graphics.Color;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

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
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class SearchFragmentTest {
  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);
  
  @Test
  public void allViewsExist() {
    onView(withId(R.id.tv_artistSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_albumSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_trackSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_groupSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.tv_playlistSlicer_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.fab_addButton_fragmentSearch)).check(matches(isDisplayed()));
    onView(withId(R.id.rv_fragmentSearch)).check(matches(isDisplayed()));
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
  
  @Test
  public void createGroup() {
    onView(withId(R.id.fab_addButton_fragmentSearch)).perform(click());
    onView(withId(R.id.et_name_dialogAdd)).perform(typeText("aaa New Group"));
    onView(withText("Create Group")).perform(click());
    
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("aaa New Group")));
    pressBack();
    
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("MusicBox")));
  }
  
  @Test
  public void clickGroupGoesToGroup() {
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.actionOnItem(hasDescendant(withText("aaa Empty Group")), click()));
  
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("aaa Empty Group")));
  }
  
  @Test
  public void sliceForGroupsShowsOnlyGroups() {
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
    // no playlists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("aaa Empty Playlist")))));
    
    // a group is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("Brandenburg Concerto"))));
  }
  
  @Test
  public void createPlaylist() {
    onView(withId(R.id.fab_addButton_fragmentSearch)).perform(click());
    onView(withId(R.id.et_name_dialogAdd)).perform(typeText("aaa New Playlist"));
    onView(withText("Create Playlist")).perform(click());
  
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("aaa New Playlist")));
    pressBack();
  
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("MusicBox")));
  }
  
  @Test
  public void clickPlaylistGoesToPlaylist() {
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.actionOnItem(hasDescendant(withText("aaa Empty Playlist")), click()));
    
    onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar")))).check(
        matches(withText("aaa Empty Playlist")));
  }
  
  @Test
  public void sliceForPlaylistsShowsOnlyPlaylists() {
    onView(withId(R.id.tv_playlistSlicer_fragmentSearch)).perform(click());
    
    // no artists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Taylor Swift")))));
    // no albums shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Red")))));
    // no tracks shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("State of Grace")))));
    // no groups shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Brandenburg Concerto")))));
    
    // a playlist is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("aaa Empty Playlist"))));
  }
  
  @Test
  public void sliceForTracksShowsOnlyTracks() {
    onView(withId(R.id.tv_trackSlicer_fragmentSearch)).perform(click());
    
    // no artists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Taylor Swift")))));
    // no albums shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Red")))));
    // no groups shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Brandenburg Concerto")))));
    // no playlists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("aaa Empty Playlist")))));
    
    // a track is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).perform(
        RecyclerViewActions.scrollTo(hasDescendant(withText("State of Grace"))));
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("State of Grace"))));
  }
  
  @Test
  public void sliceForAlbumsShowsOnlyAlbums() {
    onView(withId(R.id.tv_albumSlicer_fragmentSearch)).perform(click());
    
    // no artists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Taylor Swift")))));
    // no tracks shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("State of Grace")))));
    // no groups shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Brandenburg Concerto")))));
    // no playlists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("aaa Empty Playlist")))));
  
    // an album is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("Red"))));
  }
  
  @Test
  public void sliceForArtistsShowsOnlyArtists() {
    onView(withId(R.id.tv_artistSlicer_fragmentSearch)).perform(click());
    
    // no albums shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Red")))));
    // no tracks shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("State of Grace")))));
    // no groups shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("Brandenburg Concerto")))));
    // no playlists shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(not(hasDescendant(withText("aaa Empty Playlist")))));
  
    // an artist is shown
    onView(allOf(withId(R.id.rv_fragmentSearch), isDisplayed())).check(
        matches(hasDescendant(withText("Taylor Swift"))));
  }
}
