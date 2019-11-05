package pyk.musicbox.contract.listener;

public interface Listener {
  public interface FragmentListener {
    void updateTitle(String newTitle);
  }
  
  public interface PlaybackControlListener {
    void playToggle(String id);
  }
}
