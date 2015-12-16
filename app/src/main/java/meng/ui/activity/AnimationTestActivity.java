package meng.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import meng.ui.view.StatusBar;

import meng.olladroid.R;

public class AnimationTestActivity extends AppCompatActivity implements SpringListener {

    private static final String TAG = AnimationTestActivity.class.getSimpleName();
    @InjectView(R.id.spring_value)
    TextView currValueView;
    @InjectView(R.id.end_value)
    EditText endValueView;
    SpringSystem springSystem;
    Spring spring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        ButterKnife.inject(this);
        springSystem = SpringSystem.create();
        spring  = springSystem.createSpring();
        spring.addListener(this);
    }

    public void onClick(View v) {
         spring.setEndValue(Double.valueOf(endValueView.getText().toString()));
    }

    @Override
    public void onSpringUpdate(Spring spring) {
        currValueView.setText("" + spring.getCurrentValue());
    }

    @Override
    public void onSpringAtRest(Spring spring) {
        Log.d(TAG, "onSpringAtRest: ");
        Toast.makeText(this, "At Rest", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSpringActivate(Spring spring) {
        Log.d(TAG, "onSpringActivate: ");
    }

    @Override
    public void onSpringEndStateChange(Spring spring) {
        Log.d(TAG, "onSpringEndStateChange: ");
    }
}
