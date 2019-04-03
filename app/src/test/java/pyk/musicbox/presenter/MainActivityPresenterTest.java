package pyk.musicbox.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pyk.musicbox.contract.activity.MainActivityContract;

import static org.mockito.Mockito.verify;

public class MainActivityPresenterTest {
  
  @Mock
  private MainActivityContract.MainActivityView activityView;
  
  private MainActivityPresenter activityPresenter;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    activityPresenter = new MainActivityPresenter(activityView);
  }
  
  @Test
  public void tileTapped() {
    activityPresenter.tileTapped();
    verify(activityView).showToast();
  }
}