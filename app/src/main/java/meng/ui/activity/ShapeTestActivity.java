package meng.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import meng.olladroid.R;

public class ShapeTestActivity extends AppCompatActivity {

    @InjectView(R.id.mobile_number)
    EditText mobileNumberView;
    @InjectView(R.id.mobile_number_label)
    TextView mobileNumberLableView;
    @InjectView(R.id.html_text_view)
    TextView htmlTextView;
    @InjectView(R.id.text_input_layout)
    TextInputLayout textInputLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        ButterKnife.inject(this);
        String s = getString(R.string.welcome_to_x, getString(R.string.app_name_reg_title));
        htmlTextView.setText(Html.fromHtml(s));
        textInputLayout.setHint("Mobile Number");
        mobileNumberView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mobileNumberLableView.setVisibility(View.VISIBLE);
                } else {
                    mobileNumberLableView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
