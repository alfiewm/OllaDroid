package meng.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import meng.olladroid.R;

public class SwipeRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private String[] dataStrings = {"Bill", "Molly", "Cassie", "Pony", "Jony", "Tony", "Luny"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        ListView list = (ListView) findViewById(R.id.swipe_list);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataStrings));
    }

}
