package pyk.musicbox.contract;

public interface FragmentContract {
  interface FragmentPresenter {
    void tileTapped(MainActivityContract.MainActivityView mainActivityView, String fragment,
                    boolean replace);
  }
}
