package com.mengwang.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mengwang.guessyourfav.R;
import com.mengwang.ui.view.RangeSeekBar;

public class RangeSeekActivity extends ActionBarActivity {
  private static int MIN_SEEK_BAR_VALUE = 0;
  private static int MAX_SEEK_BAR_VALUE = 1000;

  RangeSeekBar<Integer> rangeSeekBar;
  EditText minValueView;
  EditText maxValueView;
  Button btnReset;
  Button btnObtainValue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_range_seek);
    minValueView = (EditText) findViewById(R.id.min_value);
    maxValueView = (EditText) findViewById(R.id.max_value);
    btnReset = (Button) findViewById(R.id.btn_reset);
    btnObtainValue = (Button) findViewById(R.id.btn_obtain_value);
    rangeSeekBar = (RangeSeekBar<Integer>) findViewById(R.id.range_seek_bar);
    rangeSeekBar.setRangeValues(MIN_SEEK_BAR_VALUE, MAX_SEEK_BAR_VALUE);
    rangeSeekBar.setValueFormatter(new PriceValueFormatter(this, 0/*actual min price*/, 500/*actual max price*/, "Rs."/*currency*/));
  }

  private static class PriceValueFormatter implements RangeSeekBar.ValueFormatter<Integer> {
    private final static float DIVISION_POINT_PERCENTAGE = 0.2f; // Seek range before the division point will take half.
    private static final int DIVISION_SEEK_BAR_VALUE = MAX_SEEK_BAR_VALUE / 2;

    private int m_minDisplayValue;
    private int m_maxDisplayValue;
    private int m_baseScale;
    private int priceRange;
    private Context m_context;
    private String m_currency = "";

    private PriceValueFormatter(Context context, int minValue, int maxValue, String currency) {
      m_context = context;
      m_minDisplayValue = minValue;
      m_maxDisplayValue = maxValue;
      m_currency = currency;
      priceRange = m_maxDisplayValue - m_minDisplayValue;
      m_baseScale = (int) Math.ceil(priceRange*DIVISION_POINT_PERCENTAGE/DIVISION_SEEK_BAR_VALUE);
    }

    @Override
    public String format(Integer value) {
      if (value <= MIN_SEEK_BAR_VALUE) {
        return m_context.getResources().getString(R.string.shop_search_price_no_min);
      } else if (value >= MAX_SEEK_BAR_VALUE) {
        return m_currency + String.format("%d+", m_maxDisplayValue);
      } else {
        return m_currency + mapSeekBarValueToDisplayValue(value).toString();
      }
    }

    public Integer mapSeekBarValueToDisplayValue(Integer seekBarValue) {
      float result;
      if (seekBarValue <= DIVISION_SEEK_BAR_VALUE) {
        result = m_minDisplayValue + (seekBarValue * priceRange * DIVISION_POINT_PERCENTAGE) / DIVISION_SEEK_BAR_VALUE;
        return round(result, m_baseScale);
      } else {
        result = m_minDisplayValue + priceRange * DIVISION_POINT_PERCENTAGE
                + priceRange * (1 - DIVISION_POINT_PERCENTAGE) * (seekBarValue - DIVISION_SEEK_BAR_VALUE) / (MAX_SEEK_BAR_VALUE - DIVISION_SEEK_BAR_VALUE);
        return round(result, m_baseScale*10);
      }
    }

    public Integer mapDisplayValueToSeekBarValue(Integer displayValue) {
      if (displayValue - m_minDisplayValue <= priceRange * DIVISION_POINT_PERCENTAGE) {
        return (int) ((displayValue - m_minDisplayValue) * DIVISION_SEEK_BAR_VALUE / (priceRange * DIVISION_POINT_PERCENTAGE));
      } else {
        return DIVISION_SEEK_BAR_VALUE
                + Math.round(((displayValue - m_minDisplayValue - priceRange * DIVISION_POINT_PERCENTAGE) * (MAX_SEEK_BAR_VALUE - DIVISION_SEEK_BAR_VALUE) / (priceRange * (1 - DIVISION_POINT_PERCENTAGE))));
      }
    }
  }

  public static int round(float i, int v) {
    return Math.round(i/v)*v;
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_reset:
        rangeSeekBar.setValueFormatter(new PriceValueFormatter(this, Integer.valueOf(minValueView.getText().toString()),
                Integer.valueOf(maxValueView.getText().toString()), "Rs."));
        rangeSeekBar.resetSelectedValues();
        break;
      case R.id.btn_set:
        rangeSeekBar.setSelectedMinValue(((PriceValueFormatter) rangeSeekBar.getValueFormatter()).mapDisplayValueToSeekBarValue(Integer.valueOf(minValueView.getText().toString())));
        rangeSeekBar.setSelectedMaxValue(((PriceValueFormatter) rangeSeekBar.getValueFormatter()).mapDisplayValueToSeekBarValue(Integer.valueOf(maxValueView.getText().toString())));
        break;
      case R.id.btn_obtain_value:
        Integer minValue = rangeSeekBar.getSelectedMinValue();
        Integer maxValue = rangeSeekBar.getSelectedMaxValue();
        Toast.makeText(this, "seekbar min = " + minValue + " seekbar max = " + maxValue
                + "\nprice min = " + ((PriceValueFormatter) rangeSeekBar.getValueFormatter()).mapSeekBarValueToDisplayValue(minValue)
                + " price max = " + ((PriceValueFormatter) rangeSeekBar.getValueFormatter()).mapSeekBarValueToDisplayValue(maxValue)
                , Toast.LENGTH_SHORT).show();
        break;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_range_seek, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
