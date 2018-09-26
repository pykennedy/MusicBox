package pyk.musicbox.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pyk.musicbox.R;
import pyk.musicbox.contract.MainActivityContract;
import pyk.musicbox.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.MainActivityView {
  
  private MainActivityPresenter mainActivityPresenter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainActivityPresenter = new MainActivityPresenter(this);
  }
  
  @Override
  public void showToast() {
    Toast.makeText(this, "it worked", Toast.LENGTH_SHORT).show();
  }
}
