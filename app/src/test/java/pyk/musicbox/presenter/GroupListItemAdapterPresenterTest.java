package pyk.musicbox.presenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import pyk.musicbox.contract.GroupListItemAdapterContract;
import pyk.musicbox.model.dbobjects.Group;

public class GroupListItemAdapterPresenterTest {
  @Mock
  private GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  
  private GroupListItemAdapterPresenter gliap;
  
  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    gliap = new GroupListItemAdapterPresenter(gliav);
  }
  
  @Test
  public void getGroupsFromDB() {
    List<Group> groups = gliap.getGroupsFromDB();
    Assert.assertEquals(2, groups.get(2).getGroupID());
  }
}
