package pyk.musicbox.contract;

public interface HomeFragmentContract {
  interface HomeFragmentView {
  
  }
  
  interface HomeFragmentPresenter {
    void tileTapped(MainActivityContract.MainActivityView mainActivityView, String fragment);
  }
}
