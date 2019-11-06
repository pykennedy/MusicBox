package pyk.musicbox.contract.callback;

public interface Callback {
  public interface InsertGroupCB {
    public void onResponse(boolean succeeded, String msg);
  }
  
  public interface InsertPlaylistCB {
    public void onResponse(boolean succeeded, String msg);
  }
  
  public interface InitPlaylistCB {
    public void onComplete(boolean succeeded, String msg);
  }
}
