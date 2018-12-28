package pyk.musicbox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pyk.musicbox.view.activity.MainActivityTest;
import pyk.musicbox.view.fragment.HomeFragmentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityTest.class, HomeFragmentTest.class})
public class AndroidTestSuite {}