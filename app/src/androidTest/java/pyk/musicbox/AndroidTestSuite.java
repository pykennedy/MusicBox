package pyk.musicbox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pyk.musicbox.model.MBDBUnitTest;
import pyk.musicbox.view.activity.MainActivityTest;
import pyk.musicbox.view.fragment.GroupFragmentTest;
import pyk.musicbox.view.fragment.SearchFragmentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
    {MainActivityTest.class, SearchFragmentTest.class, GroupFragmentTest.class, MBDBUnitTest.class})
public class AndroidTestSuite {}