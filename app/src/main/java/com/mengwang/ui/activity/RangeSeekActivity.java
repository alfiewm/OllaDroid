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
    rangeSeekBar.setValueFormatter(new PriceValueFormatter(this, 0, 500, "Rs."));
  }

  private static class PriceValueFormatter implements RangeSeekBar.ValueFormatter<Integer> {
    private final static double DIVISION_POINT_PERCENTAGE = 0.2; // Seek range before the division point will take half.
    private static int MIN_SEEK_BAR_VALUE = 0;
    private static int MAX_SEEK_BAR_VALUE = 1000;
    private static final int DIVISION_SEEK_BAR_VALUE = MAX_SEEK_BAR_VALUE / 2;

    private int m_minDisplayValue;
    private int m_maxDisplayValue;
    private Context m_context;
    private String m_currency = "";
    private int priceRange;

    private PriceValueFormatter(Context context, int minValue, int maxValue, String currency) {
      m_context = context;
      m_minDisplayValue = minValue;
      m_maxDisplayValue = maxValue;
      m_currency = currency;
      priceRange = m_maxDisplayValue - m_minDisplayValue;
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
      if (seekBarValue <= DIVISION_SEEK_BAR_VALUE) {
        return m_minDisplayValue + Math.round(((float) (seekBarValue * priceRange * DIVISION_POINT_PERCENTAGE)) / DIVISION_SEEK_BAR_VALUE);
      } else {
        return m_minDisplayValue + (int) Math.round(priceRange * DIVISION_POINT_PERCENTAGE)
                + (int) Math.round((float) priceRange * (1 - DIVISION_POINT_PERCENTAGE) * (seekBarValue - DIVISION_SEEK_BAR_VALUE) / (MAX_SEEK_BAR_VALUE - DIVISION_SEEK_BAR_VALUE));
      }
    }

    public Integer mapDisplayValueToSeekBarValue(Integer displayValue) {
      if (displayValue - m_minDisplayValue <= priceRange * DIVISION_POINT_PERCENTAGE) {
        return (int) ((double) (displayValue - m_minDisplayValue) * DIVISION_SEEK_BAR_VALUE / (priceRange * DIVISION_POINT_PERCENTAGE));
      } else {
        return DIVISION_SEEK_BAR_VALUE
                + (int) Math.round(((float) (displayValue - m_minDisplayValue - priceRange * DIVISION_POINT_PERCENTAGE) * (MAX_SEEK_BAR_VALUE - DIVISION_SEEK_BAR_VALUE) / (priceRange * (1 - DIVISION_POINT_PERCENTAGE))));
      }
    }
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
