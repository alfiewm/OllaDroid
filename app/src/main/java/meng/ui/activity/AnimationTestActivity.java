package meng.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import meng.olladroid.R;

public class AnimationTestActivity extends AppCompatActivity implements SpringListener {

    private static final String TAG = AnimationTestActivity.class.getSimpleName();
    @InjectView(R.id.spring_value)
    TextView currValueView;
    @InjectView(R.id.end_value)
    EditText endValueView;
    @InjectView(R.id.random)
    Button button;
    @InjectView(R.id.text_switcher)
    TextSwitcher textSwitcher;
    @InjectView(R.id.btn_change_text)
    Button bTnChangeText;
    SpringSystem springSystem;
    Spring spring;
    private Animation inAnimation;
    private Animation outAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        ButterKnife.inject(this);
        springSystem = SpringSystem.create();
        spring = springSystem.createSpring();
        spring.addListener(this);
        endValueView.setText("10");
        currValueView.setText("10");
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(250);
        inAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(250);
        outAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        textSwitcher.setInAnimation(inAnimation);
        textSwitcher.setOutAnimation(outAnimation);
    }

    public void onClick(View v) {
        spring.setEndValue(Double.valueOf(endValueView.getText().toString()));
        Rect mOriginalRect = new Rect();
        mOriginalRect.set(button.getLeft(), button.getTop(), button.getRight(), button.getBottom());

        TranslateAnimation anim = new TranslateAnimation(200, 400, 200, 400);
        anim.setDuration(300);
        anim.setFillAfter(true);
//        anim.setFillBefore(true);
        button.startAnimation(anim);

        button.layout(mOriginalRect.left, mOriginalRect.top, mOriginalRect.right, mOriginalRect.bottom);
    }

    private static boolean longText = false;

    @OnClick(R.id.btn_change_text)
    public void changeText(View v) {
        if (longText) {
            textSwitcher.setText("release to cancel");
        } else {
            textSwitcher.setText("release to cancel, release to cancel");
        }
        longText = !longText;
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
