package pyk.musicbox.presenter;

import pyk.musicbox.model.GroupList;

public class GroupListItemAdapterPresenterTest {
  GroupListItemAdapterPresenter gliap;
  GroupList                     groupList;
  /*
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
  }*/
}
