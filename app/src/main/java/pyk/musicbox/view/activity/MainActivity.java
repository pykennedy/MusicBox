package pyk.musicbox.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.ActivityContract;
import pyk.musicbox.presenter.ActivityPresenter;

public class MainActivity extends AppCompatActivity implements ActivityContract.ActivityView {
  
  private ActivityPresenter activityPresenter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    activityPresenter = new ActivityPresenter(this);
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }
}
