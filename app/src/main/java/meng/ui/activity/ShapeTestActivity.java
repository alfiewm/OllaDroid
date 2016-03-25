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
import butterknife.Bind;
import butterknife.OnClick;
import meng.Utils;
import meng.olladroid.R;
import meng.ui.view.PinEntryView;

public class ShapeTestActivity extends AppCompatActivity {

    @Bind(R.id.html_text_view)
    TextView htmlTextView;
    @Bind(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @Bind(R.id.pin_entry_view)
    PinEntryView pinEntryView;
    @Bind(R.id.textDialog)
    EditText textInputEditText;
    @Bind(R.id.view_switcher)
    ViewSwitcher viewSwitcher;
    @Bind(R.id.toolbar_switcher)
    ViewSwitcher toolbarSwitcher;
    @Bind(R.id.activity_toolbar)
    Toolbar activityToolbar;
    @Bind(R.id.drawer_toolbar)
    Toolbar drawerToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        ButterKnife.bind(this);
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
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_set_pin_error, R.id.btn_clear_pin_error, R.id.btn_clear_text, R.id.view_switcher, R.id.toolbar_switcher})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_pin_error:
                Utils.toggleSoftInput(ShapeTestActivity.this, true);
                pinEntryView.setDigitColor(Color.RED);
                break;
            case R.id.btn_clear_pin_error:
                Utils.hideKeyboard(this, textInputEditText);
                textInputEditText.requestFocus();
                Utils.showKeyboard(this, textInputEditText, true);
                pinEntryView.setDigitColor(getResources().getColor(android.R.color.black));
                break;
            case R.id.btn_clear_text:
                textInputEditText.requestFocus();
                Utils.showKeyboard(this, textInputEditText, true);
                Utils.hideKeyboard(this, textInputEditText);
                textInputEditText.requestFocus();
                Utils.showKeyboard(this, textInputEditText, true);
                Utils.hideKeyboard(this, textInputEditText);
                pinEntryView.clearText();
                break;
            case R.id.view_switcher:
                textInputEditText.requestFocus();
                Utils.showKeyboard(this, textInputEditText, true);
                toolbarSwitcher.setDisplayedChild(toolbarSwitcher.getDisplayedChild() == 0 ? 1 : 0);
                break;
            case R.id.toolbar_switcher:
                Utils.hideKeyboard(this, textInputEditText);
                toolbarSwitcher.showNext();
                break;
            default:
                break;
        }
    }
}
