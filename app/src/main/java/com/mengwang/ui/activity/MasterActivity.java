package com.mengwang.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mengwang.guessyourfav.R;

public class MasterActivity extends Activity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_master);
    findViewById(R.id.root).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
//    findViewById(R.id.btn_range_seekbar).performClick();
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.control_camera:
        Intent it = new Intent(this, UsingCameraActivity.class);
        startActivity(it);
        break;
      case R.id.animation_test:
        startActivity(new Intent(this, AnimationTestActivity.class));
        break;
      case R.id.swipe_refresh:
        startActivity(new Intent(this, SwipeRefreshActivity.class));
        break;
      case R.id.shape_test:
        startActivity(new Intent(this, ShapeTestActivity.class));
        break;
      case R.id.action_bar_test:
        startActivity(new Intent(this, ActionBarTestActivity.class));
        break;
      case R.id.webview_cookie:
        BrowserActivity.launchBrowser("http://m.aliexpress.com", this, null);
        break;
      case R.id.btn_range_seekbar:
        startActivity(new Intent(this, RangeSeekActivity.class));
        break;
      default:
        break;
    }
  }
}
