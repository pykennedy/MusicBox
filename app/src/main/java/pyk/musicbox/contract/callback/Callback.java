package pyk.musicbox.contract.callback;

import pyk.musicbox.model.entity.Track;

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
  
  public interface moveHeadCB {
    public void onComplete(boolean succeeded, String msg);
  }
  
  public interface FirstTrackCB {
    public void onComplete(boolean succeeded, Track track);
  }
}
