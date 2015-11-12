package meng;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
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

}
