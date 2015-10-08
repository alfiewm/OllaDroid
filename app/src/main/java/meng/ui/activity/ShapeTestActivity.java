package meng.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        ButterKnife.inject(this);
        String s = getString(R.string.welcome_to_x, getString(R.string.app_name_reg_title));
        htmlTextView.setText(Html.fromHtml(s));
        textInputLayout.setHint("Mobile Number");
        textInputEditText.setTextSize(11);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_set_pin_error, R.id.btn_clear_pin_error, R.id.btn_clear_text})
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
            default:
                break;
        }
    }
}
