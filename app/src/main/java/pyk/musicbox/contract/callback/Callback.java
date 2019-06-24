package pyk.musicbox.contract.callback;

public interface Callback {
  public interface InsertGroupCB {
    public void onResponse(boolean succeeded, String msg);
  }
}
