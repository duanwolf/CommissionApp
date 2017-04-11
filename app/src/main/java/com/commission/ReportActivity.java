package com.commission;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commission.yore.commission.Business;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    Business business;
    private TextView update_time;
    private TextView num_barrels;
    private TextView num_stocks;
    private TextView num_lock;
    private TextView money;
    private float[] bili;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String[] lastFive = bundle.getStringArray("lastfive");
            ArrayList<String> list1 = bundle.getStringArrayList("result");
            setContentView(R.layout.report);
            initView(list1, lastFive);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        business = (Business) getIntent().getSerializableExtra("Business");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                ArrayList<String> result = business.getStatistics(year, month + 1);
                String[] lastFive = business.getLastFiveMonthInfo();
                Bundle bundle = new Bundle();
                bundle.putStringArray("lastfive", lastFive);
                bundle.putStringArrayList("result", result);
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.detail) {
            Intent i = new Intent(ReportActivity.this, PictureAcitivty.class);
            i.putExtra("Business", business);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initView(ArrayList<String> list1, final String[] lastfive) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.char_container);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        LineChart chart = new LineChart(ReportActivity.this);
        LineChart.LayoutParams params = new LineChart.LayoutParams(width, width);
        chart.setLayoutParams(params);
        layout.addView(chart);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return lastfive[(int) value];
            }
        });
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new Entry(i, Float.valueOf(lastfive[i+5])));
        }
        LineDataSet data = new LineDataSet(entries, "销量");
        data.setColor(getResources().getColor(R.color.dodger_blue));
        LineData ld = new LineData(data);
        chart.setData(ld);
        chart.invalidate();
        update_time = (TextView) findViewById(R.id.update_time);
        num_barrels = (TextView) findViewById(R.id.num_barrels);
        num_stocks = (TextView) findViewById(R.id.num_stocks);
        num_lock = (TextView) findViewById(R.id.num_locks);
        money = (TextView) findViewById(R.id.money);
        if (list1.size() != 0) {
            bili = new float[3];
            money.setText(list1.get(0) + "$");
            num_lock.setText(list1.get(1) + "个, 总共$" + (Integer.valueOf(list1.get(1)) * 45));
            num_stocks.setText(list1.get(2) + "个, 总共$" + (Integer.valueOf(list1.get(2)) * 30));
            num_barrels.setText(list1.get(3) + "个, 总共$" + (Integer.valueOf(list1.get(1)) * 25));
            int lnum = Integer.valueOf(list1.get(1));
            int snum = Integer.valueOf(list1.get(2));
            int bnum = Integer.valueOf(list1.get(3));
            int sum = lnum + snum + bnum;
            bili = new float[3];
            bili[0] = ((float) lnum / sum) * 100;
            bili[1] = ((float) bnum / sum) * 100;
            bili[2] = ((float) snum / sum) * 100;
            update_time.setText(list1.get(4));
        } else {
            money.setText("Not update yet!");
            num_lock.setVisibility(View.GONE);
            num_stocks.setVisibility(View.GONE);
            num_barrels.setVisibility(View.GONE);
            update_time.setVisibility(View.GONE);
        }
        Log.d("result0", list1.toString());

    }

}
