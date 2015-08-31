package meng.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import meng.olladroid.R;

public class ShapeTestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        String s = getString(R.string.welcome_to_x, getString(R.string.app_name_reg_title));
        ((TextView) findViewById(R.id.html_text_view)).setText(Html.fromHtml(s));
    }
}
