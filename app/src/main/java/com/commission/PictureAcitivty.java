package com.commission;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.commission.yore.commission.Business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PictureAcitivty extends AppCompatActivity{
    private ListView list;
    private final String[] array = {"时间", "地点"};
    Business business;
    private int type = 0;
    private LinearLayout second;
    private TextView mmonth;
    private EditText month;
    private TextView years;
    private EditText year;
    private TextView first;
    private Button quert;
    private MyAdapter adapter;
    Handler handler = new Handler(Looper.getMainLooper()) {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ArrayList<ArrayList<String>> result = (ArrayList<ArrayList<String>>) msg.obj;
                    setContentView(R.layout.activity_picture);
                    initView(result);
                    break;
                case 1:
                    ArrayList<String[]> result1 = (ArrayList<String[]>) msg.obj;
                    System.out.println(result1);
                    ArrayList<ArrayList<String>> list = new ArrayList<>();
                    for (int i = 0; i < result1.size(); i++) {
                        String[] item = result1.get(i);
                        ArrayList<String> as = new ArrayList<>();
                        for(String it : item) {
                            as.add(it);
                        }
                        list.add(as);

                    }
                    adapter.changeData(list);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        business = (Business)getIntent().getSerializableExtra("Business");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                ArrayList<ArrayList<String>> result = business.getMonthlyDetail(year, month + 1);
                Message msg = new Message();
                msg.obj = result;
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }).start();


    }

    class MyAdapter extends BaseAdapter {
        private ArrayList<ArrayList<String>> lists;

        public MyAdapter(ArrayList<ArrayList<String>> lists) {
            super();
            this.lists = lists;
        }

        public void changeData(ArrayList<ArrayList<String>> list) {
            lists = list;
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ArrayList<String> list = lists.get(position);
            holder.city_time.setText(list.get(0));
            holder.locks.setText(list.get(1) + "个, 总共$" + (Integer.valueOf(list.get(1)) * 45));
            holder.stocks.setText(list.get(2) + "个, 总共$" + (Integer.valueOf(list.get(2)) * 30));
            holder.barrels.setText(list.get(3) + "个, 总共$" + (Integer.valueOf(list.get(1)) * 25));
            return convertView;
        }


        @Override
        public int getCount() {
            return lists.size();
        }

        public class ViewHolder {
            public View rootView;
            public TextView city_time;
            public TextView locks;
            public TextView stocks;
            public TextView barrels;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.city_time = (TextView) rootView.findViewById(R.id.city_time);
                this.locks = (TextView) rootView.findViewById(R.id.locks);
                this.stocks = (TextView) rootView.findViewById(R.id.stocks);
                this.barrels = (TextView) rootView.findViewById(R.id.barrels);
            }

        }
    }

    private void initView(ArrayList<ArrayList<String>> result) {
        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(result);
        list.setAdapter(adapter);
        second = (LinearLayout) findViewById(R.id.second);
        mmonth = (TextView) findViewById(R.id.mmonth);
        month = (EditText) findViewById(R.id.month);
        first = (TextView) findViewById(R.id.first);
        quert = (Button) findViewById(R.id.quert);
        year = (EditText) findViewById(R.id.year);
        years = (TextView) findViewById(R.id.years);
        Spinner spinner = (Spinner) findViewById(R.id.spi);
        spinner.setAdapter(new ArrayAdapter<String>(PictureAcitivty.this, android.R.layout.simple_spinner_item, array));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
                switch (position) {
                    case 0:
                        first.setText("时间");
                        second.setVisibility(View.VISIBLE);
                        years.setVisibility(View.VISIBLE);
                        year.setText("");
                        break;
                    case 1:
                        first.setText("城市");
                        second.setVisibility(View.GONE);
                        years.setVisibility(View.GONE);
                        year.setText("");
                        month.setText("");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> view) {

            }
        });
        quert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case 0:
                        final String strYear = year.getText().toString();
                        final String strMon = month.getText().toString();
                        if (strYear.equals("") || strMon.equals("")) {
                            Toast.makeText(PictureAcitivty.this, "请输入时间", Toast.LENGTH_SHORT).show();
                        } else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ArrayList<String[]> result = business.getMonthlyReportByTown(Integer.valueOf(strYear),
                                            Integer.valueOf(strMon), null);
                                    Message msg = new Message();
                                    msg.obj = result;
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                        }
                        break;
                    case 1:
                        final String city = year.getText().toString();
                        final String city1 = city.trim();
                        if (city.equals("")) {
                            Toast.makeText(PictureAcitivty.this, "请输入城市", Toast.LENGTH_SHORT).show();
                        } else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ArrayList<String[]> result = business.getMonthlyReportByTown(0, 0, city1);
                                    System.out.println(result);
                                    Message msg = new Message();
                                    msg.obj = result;
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                        }
                        break;
                }
            }
        });
    }


}
