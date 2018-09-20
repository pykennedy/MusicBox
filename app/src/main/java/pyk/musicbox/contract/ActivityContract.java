package pyk.musicbox.contract;

public interface ActivityContract {
  interface ActivityView {
    void showToast();
  }
  
  
  interface ActivityPresenter {
    void tileTapped();
  }
  
  
}
