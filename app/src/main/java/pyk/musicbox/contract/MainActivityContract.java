package pyk.musicbox.contract;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
    void swapFragment(String fragment);
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
  }
  
  
}