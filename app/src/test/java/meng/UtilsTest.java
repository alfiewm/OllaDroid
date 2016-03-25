package meng;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.widget.FrameLayout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import meng.olladroid.BuildConfig;

/**
 * Created by meng on 16/3/19.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        application = Application.class)
public class UtilsTest {

    private Activity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(Activity.class);
        FrameLayout container = new FrameLayout(activity);
        activity.setContentView(container);
    }

    @Test
    public void testOrientation() {
        Assert.assertEquals(Utils.getOrientation(activity), Configuration.ORIENTATION_PORTRAIT);
    }
}
