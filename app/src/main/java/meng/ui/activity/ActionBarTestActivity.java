package meng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.ButterKnife;
import butterknife.OnClick;
import meng.Utils;
import meng.olladroid.R;

public class ActionBarTestActivity extends AppCompatActivity {

    public static final String[] albamList = {"nimei", "nima", "nidaye"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_test);
        ButterKnife.inject(this);
        ActionBar actionbar = getSupportActionBar();
        Spinner actionbarSpinner = new Spinner(this);
        actionbarSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, albamList));
        actionbar.setCustomView(new Spinner(this));
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle("ABT");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    boolean locked = false;
    @OnClick(R.id.orientation_lock)
    public void onClick() {
        locked = !locked;
        setScreenOrientationLocked(locked);
    }

    private void setScreenOrientationLocked(boolean locked) {
        if (!Utils.isScreenOrientationEnabled(this)) {
            return;
        }
        setRequestedOrientation(locked ? Utils.getOrientation(this) : ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings0) {
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_action_bar_test, container, false);
        }

        @Override
        public void onResume() {
            super.onResume();
            getView().findViewById(R.id.camera_activity).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), SearchViewActivity.class);
                    startActivity(it);
                }
            });
        }
    }
}
