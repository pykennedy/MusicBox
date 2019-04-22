package pyk.musicbox.presenter;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pyk.musicbox.contract.adapter.GroupListItemAdapterContract;
import pyk.musicbox.contract.callback.Callback;
import pyk.musicbox.model.GroupList;
import pyk.musicbox.model.database.DBHelper;
import pyk.musicbox.model.entity.Group;
import pyk.musicbox.model.entity.GroupOld;

import static org.mockito.Mockito.verify;

public class GroupListItemAdapterPresenterTest {
  GroupListItemAdapterPresenter gliap;
  GroupList                     groupList;
  
  @Mock
  private DBHelper                                              dbHelper;
  @Mock
  private GroupListItemAdapterContract.GroupListItemAdapterView gliav;
  
  @Captor
  private ArgumentCaptor<Callback.GroupListCB> captor;
  
  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    groupList = GroupList.getInstance();
    gliap = new GroupListItemAdapterPresenter(gliav, groupList, dbHelper);
  }
  
  @After
  public void cleanup() { groupList.getGroupOlds().clear(); }
  
  //TODO: make mock classes that don't thread long running work so these unit tests can pass.
  
  @Test
  public void populateGroupList_CallsDBHelper() {
    gliap.populateGroupList();
    verify(dbHelper).populateGroupList(captor.capture());
  }
  
  @Test
  public void populateGroupList_CallsTriggerRefresh() {
    gliap = new GroupListItemAdapterPresenter(gliav);
    gliap.populateGroupList();
    verify(gliav).triggerRefresh();
  }
  
  @Test
  public void getCount() {
    gliap = new GroupListItemAdapterPresenter(gliav);
    gliap.populateGroupList();
    int count = gliap.getItemCount();
    Assert.assertEquals(20, count);
  }
  
  @Test
  public void getGroup() {
    gliap = new GroupListItemAdapterPresenter(gliav);
    gliap.populateGroupList();
    Group groupOld;
    groupOld = gliap.getGroupFromList(2);
    Assert.assertEquals("GroupOld #2", groupOld.getGroupTitle());
    groupOld = gliap.getGroupFromList(17);
    Assert.assertEquals("GroupOld #17", groupOld.getGroupTitle());
  }
}
