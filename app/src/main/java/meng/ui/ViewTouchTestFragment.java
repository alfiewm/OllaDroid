package meng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import meng.Utils;
import meng.olladroid.R;
import meng.ui.view.test.MyEditText;
import meng.ui.view.test.MyView;
import meng.ui.view.test.MyViewGroup;

/**
 * Created by mengw on 15/12/16.
 */
public class ViewTouchTestFragment extends Fragment {

    private static final String TAG = ViewTouchTestFragment.class.getSimpleName();
    @InjectView(R.id.my_edit_text)
    MyEditText myEditText;
    @InjectView(R.id.system_edit_text)
    EditText systemEditText;
    @InjectView(R.id.my_view_group)
    MyViewGroup myViewGroup;
    @InjectView(R.id.my_view)
    MyView myView;

    public ViewTouchTestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_touch_test, container, false);
        ButterKnife.inject(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
        myViewGroup.setClickable(true);
        myView.setClickable(true);
        myViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "myViewGroup onTouch: event =" + Utils.motionEventToString(event));
                return false;
            }
        });
        
        myEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "myEditText onTouch: event =" + Utils.motionEventToString(event));
                return false;
            }
        });
        
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "myView onTouch: event =" + Utils.motionEventToString(event));
                return false;
            }
        });
    }
}
