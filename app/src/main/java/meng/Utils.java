package meng;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by mengw on 15/11/11.
 */
public class Utils {
    // git testx
    public static final String TAG = Utils.class.getSimpleName();

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context, View view, boolean force) {
        if (!hasRunningActivity(context)) {
            Log.w(TAG, "Trying to open keyboard when activity is closed");
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, force ? InputMethodManager.SHOW_FORCED : InputMethodManager.SHOW_IMPLICIT);
    }

    public static boolean hasRunningActivity(Context context) {
        if (context instanceof Activity) {
            return !((Activity) context).isFinishing();
        }

        if (context instanceof ContextThemeWrapper) {
            // non activity context
            Context baseContext = ((ContextThemeWrapper) context).getBaseContext();
            if (baseContext != context)
                return hasRunningActivity(baseContext);
        }

        return false;
    }

    public static void toggleSoftInput(Context context, boolean force) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(force ? InputMethodManager.SHOW_FORCED : InputMethodManager.SHOW_IMPLICIT, 0);
    }
    
    public static String motionEventToString(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "OTHER EVENT";
        }
    }

    public static int getOrientation(Context context) {
        final int orientation = context.getResources().getConfiguration().orientation;
        final int devRotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();

        switch (orientation) {
            case android.content.res.Configuration.ORIENTATION_PORTRAIT:
                if (devRotation == Surface.ROTATION_90 || devRotation == Surface.ROTATION_180) {
                    // this means the different devRotation in the tablet
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                } else {
                    return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                }
            case android.content.res.Configuration.ORIENTATION_LANDSCAPE:
                if (devRotation == Surface.ROTATION_0 || devRotation == Surface.ROTATION_90) {
                    // this means the different devRotation in the tablet
                    return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                } else {
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                }
            default:
                return ActivityInfo.SCREEN_ORIENTATION_NOSENSOR;
        }
    }

    public static boolean isScreenOrientationEnabled(Context context) {
        boolean isOrientationEnabled;
        try {
            isOrientationEnabled = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION) == 1;
        } catch (Settings.SettingNotFoundException e) {
            isOrientationEnabled = false;
        }
        return isOrientationEnabled;
    }
}
