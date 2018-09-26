package pyk.musicbox.contract;

public interface MainActivityContract {
  interface MainActivityView {
    void showToast();
  }
  
  
  interface MainActivityPresenter {
    void tileTapped();
  }
  
  
}