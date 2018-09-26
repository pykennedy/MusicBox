package pyk.musicbox.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pyk.musicbox.contract.ActivityContract;

import static org.mockito.Mockito.verify;

public class ActivityPresenterTest {
  
  @Mock
  private ActivityContract.ActivityView activityView;
  
  private ActivityPresenter activityPresenter;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    activityPresenter = new ActivityPresenter(activityView);
  }
  
  @Test
  public void tileTapped() {
    activityPresenter.tileTapped();
    verify(activityView).showToast();
  }
}