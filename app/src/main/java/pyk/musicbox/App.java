package pyk.musicbox;

import android.app.Application;

public class App extends Application {
  private static App instance = new App();
  
  public static App get() { return instance; }
  
  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }
}
