package meng.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import meng.model.User;
import meng.olladroid.R;

import butterknife.OnClick;
import meng.olladroid.databinding.ActivityDataBindBinding;

/**
 * Created by mengw on 8/16/15.
 */
public class DataBindingActivity extends AppCompatActivity {

    private User user;
    @Bind(R.dimen.activity_horizontal_margin) int horizontalMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        user = new User("Jack", "Frost");
        binding.setUser(user);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change_user)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_user:
                user.setFirstName("ShaBi");
                user.setLastName("jiuShiWo");
                break;
            default:
                break;
        }
    }
}
