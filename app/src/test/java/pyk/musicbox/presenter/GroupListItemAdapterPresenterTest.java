package pyk.musicbox.presenter;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pyk.musicbox.contract.GroupListItemAdapterContract;

public class GroupListItemAdapterPresenterTest {
  @Mock
  private GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  
  private GroupListItemAdapterPresenter gliap;
  
  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    gliap = new GroupListItemAdapterPresenter(gliav);
  }
  
  //TODO: make mock classes that don't thread long running work so these unit tests can pass.
  /*
  @Test
  public void getGroupsFromDB() {
    List<Group> groups = gliap.getGroupsFromDB();
    Assert.assertEquals(2, groups.get(2).getGroupID());
  } */
}
