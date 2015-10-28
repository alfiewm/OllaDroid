package meng.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import meng.olladroid.R;
import meng.ui.view.PinEntryView;

public class ShapeTestActivity extends AppCompatActivity {

    @InjectView(R.id.html_text_view)
    TextView htmlTextView;
    @InjectView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @InjectView(R.id.pin_entry_view)
    PinEntryView pinEntryView;
    @InjectView(R.id.textDialog)
    EditText textInputEditText;
    @InjectView(R.id.view_switcher)
    ViewSwitcher viewSwitcher;
    @InjectView(R.id.toolbar_switcher)
    ViewSwitcher toolbarSwitcher;
    @InjectView(R.id.activity_toolbar)
    Toolbar activityToolbar;
    @InjectView(R.id.drawer_toolbar)
    Toolbar drawerToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        ButterKnife.inject(this);
        String s = getString(R.string.welcome_to_x, getString(R.string.app_name_reg_title));
        htmlTextView.setText(Html.fromHtml(s));
        textInputLayout.setHint("Mobile Number");
        textInputEditText.setTextSize(11);
        activityToolbar.setTitle("Barak Obama");
        drawerToolbar.setTitle("Maps!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_set_pin_error, R.id.btn_clear_pin_error, R.id.btn_clear_text, R.id.view_switcher, R.id.toolbar_switcher})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_pin_error:
                pinEntryView.setDigitColor(Color.RED);
                break;
            case R.id.btn_clear_pin_error:
                pinEntryView.setDigitColor(getResources().getColor(android.R.color.black));
                break;
            case R.id.btn_clear_text:
                pinEntryView.clearText();
                break;
            case R.id.view_switcher:
//                viewSwitcher.showNext();
//                toolbarSwitcher.showNext();
                toolbarSwitcher.setDisplayedChild(toolbarSwitcher.getDisplayedChild() == 0 ? 1 : 0);
                break;
            case R.id.toolbar_switcher:
                toolbarSwitcher.showNext();
                break;
            default:
                break;
        }
    }
}
