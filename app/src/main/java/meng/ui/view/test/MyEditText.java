package meng.ui.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import meng.Utils;

/**
 * Created by mengw on 15/12/17.
 */
public class MyEditText extends EditText {

    public static final String TAG = MyEditText.class.getSimpleName();

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: event = " + Utils.motionEventToString(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: event = " + Utils.motionEventToString(event));
        return super.onTouchEvent(event);
    }
}
