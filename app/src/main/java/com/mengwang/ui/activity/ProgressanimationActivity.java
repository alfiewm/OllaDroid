package com.mengwang.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.mengwang.guessyourfav.R;
import com.mengwang.ui.view.SplashView;


public class ProgressAnimationActivity extends Activity {
  private SplashView splashView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_progress_animation);
    splashView = (SplashView) findViewById(R.id.splash);
    splashView.setSvgResource(R.raw.vpnswitcher);
  }

  @Override
  protected void onResume() {
    super.onResume();
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        Toast.makeText(ProgressAnimationActivity.this, "spalsh finished, exiting", Toast.LENGTH_SHORT).show();
        finish();
      }
    }, splashView.getDuration() + 1000);
  }
}
