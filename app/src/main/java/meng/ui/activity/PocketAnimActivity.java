package meng.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class PocketAnimActivity extends AppCompatActivity {

    public static final String EXTRA_TEST_DATA = "EXTRA_TEST_DATA";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(EXTRA_TEST_DATA)) {
            Toast.makeText(this, "has extra", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "no extra", Toast.LENGTH_SHORT).show();
        }
    }

}
