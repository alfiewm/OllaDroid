package meng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import meng.olladroid.R;

/**
 * Created by mengw on 15/12/16.
 */
public class ViewTouchTestFragment extends Fragment {
    public ViewTouchTestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_touch_test, container, false);
        return rootView;
    }
}
