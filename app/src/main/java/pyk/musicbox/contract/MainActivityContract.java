package pyk.musicbox.contract;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
    void swapFragment(String fragment, boolean replace);
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
  }
  
  
}