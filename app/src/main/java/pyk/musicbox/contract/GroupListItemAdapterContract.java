package pyk.musicbox.contract;

public interface GroupListItemAdapterContract {
  interface GroupListItemAdapterView {
    void inflateList();
  }
  
  interface GroupListItemAdapterPresenter {
    void getListFromDB();
  }
}
