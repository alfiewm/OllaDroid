package meng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import meng.olladroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MasterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MasterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.inject(this);
        findViewById(R.id.root).setOnClickListener(this);
        Log.d(TAG, "onCreate, action = " + getIntent().getAction());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent, action = " + intent.getAction());
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//    findViewById(R.id.btn_range_seekbar).performClick();
/*        Intent it = new Intent(this, ActionBarTestActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
        finish();*/
    }

    @OnClick({R.id.control_camera, R.id.animation_test, R.id.swipe_refresh, R.id.shape_test,
            R.id.action_bar_test, R.id.webview_cookie, R.id.btn_range_seekbar, R.id.btn_progress_animation,
            R.id.btn_data_binding})
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
            case R.id.btn_progress_animation:
                startActivity(new Intent(this, ProgressAnimationActivity.class));
                break;
            case R.id.btn_data_binding:
                startActivity(new Intent(this, DataBindingActivity.class));
                break;
            default:
                break;
        }
    }
}
