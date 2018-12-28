package pyk.musicbox.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pyk.musicbox.contract.HomeFragmentContract;
import pyk.musicbox.contract.MainActivityContract;

import static org.mockito.Mockito.verify;

public class HomeFragmentPresenterTest {
  @Mock
  private MainActivityContract.MainActivityView activityView;
  private HomeFragmentContract.HomeFragmentView homeFragmentView;
  
  private HomeFragmentPresenter homeFragmentPresenter;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    homeFragmentPresenter = new HomeFragmentPresenter(homeFragmentView);
  }
  
  @Test
  public void tileTapped() {
    homeFragmentPresenter.tileTapped(activityView, "");
    verify(activityView).swapFragment("");
  }
}
