package pyk.musicbox.utility;

import android.os.Handler;
import android.os.HandlerThread;

import pyk.musicbox.App;

public class ThreadManager extends HandlerThread {
  private static final ThreadManager instance = new ThreadManager("background");
  private              Handler       backgroundHandler;
  private Handler uiHandler;
  
  public static ThreadManager getInstance() {
    return  instance;
  }
  
  private ThreadManager(String name) {
    super(name);
    this.start();
    backgroundHandler = new Handler(getLooper());
    uiHandler = new Handler();
  }
  
  public void runTaskWithUICallback(Runnable background, Runnable callback) {
    backgroundHandler.post(background);
    uiHandler.post(callback);
  }
  
  private void setup() {
    backgroundHandler = new Handler(getLooper());
    uiHandler = new Handler(App.get().getMainLooper());
  }
  
  private void close() {
    backgroundHandler.getLooper().quit();
    uiHandler.getLooper().quit();
  }
}
