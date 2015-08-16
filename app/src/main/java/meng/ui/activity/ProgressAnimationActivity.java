package meng.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import meng.olladroid.R;
import meng.ui.view.SplashView;


public class ProgressAnimationActivity extends AppCompatActivity {
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
