package pyk.musicbox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pyk.musicbox.presenter.HomeFragmentPresenterTest;
import pyk.musicbox.presenter.MainActivityPresenterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityPresenterTest.class, HomeFragmentPresenterTest.class})
public class UnitTestSuite {}