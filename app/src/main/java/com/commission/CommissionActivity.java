package com.commission;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.commission.yore.commission.Business;

import java.util.Date;

public class CommissionActivity extends AppCompatActivity {
    private boolean isLogin;
    Business business;
    private Button button;
    private EditText city;
    private EditText bnum;
    private EditText snum;
    private EditText lnum;
    private Handler handler = new Handler(Looper.getMainLooper()) {


        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String[] result = bundle.getStringArray("result");
            switch (msg.what) {
                case 0:
                    if (result[0].equals("0")) {
                        new AlertDialog.Builder(CommissionActivity.this).setTitle("登录失败").
                                setMessage(result[1]).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                        isLogin = false;
                    } else {
                        Toast.makeText(CommissionActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                        isLogin = true;
                        setContentView(R.layout.commission);
                        initView();
                    }
                    break;
                case 1:
                    if (result[0].equals("0")) {
                        new AlertDialog.Builder(CommissionActivity.this).setTitle("上报失败").
                                setMessage(result[1]).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                        enableButton();
                    } else {
                        new AlertDialog.Builder(CommissionActivity.this).setTitle("上报成功").
                                setMessage("上报成功").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                        clear();
                    }
                    break;
            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("上报销售情况");
        String name = getIntent().getStringExtra("username");
        String psw = getIntent().getStringExtra("password");
        business = new Business(name, psw);
        Log.d("Commission", "login......" + name + "," + psw);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] result = business.login();
                Message msg = new Message();
                msg.what = 0;
                Bundle bundle = new Bundle();
                bundle.putStringArray("result", result);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.month_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.look) {

            Intent i = new Intent(CommissionActivity.this, ReportActivity.class);
            i.putExtra("Business", business);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initView() {
        bnum = (EditText) findViewById(R.id.bnum);
        lnum = (EditText) findViewById(R.id.lnum);
        snum = (EditText) findViewById(R.id.snum);
        city = (EditText) findViewById(R.id.city);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.GRAY);
                button.setText("COMMITTING...");
                button.setEnabled(false);
                String numB = bnum.getText().toString();
                String numL = lnum.getText().toString();
                String numS = snum.getText().toString();
                final String cityN = city.getText().toString();
                if (numL.equals("-1")) {

                }
                if ((numB.equals("") || numL.equals("") || numS.equals("") || cityN.equals("")) && !numL.equals("-1")) {
                    Toast.makeText(CommissionActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
                    enableButton();
                } else {
                    final Date date = new Date();
                    final int bNum = numL.equals("-1")?0:Integer.valueOf(numB);
                    final int lNum = Integer.valueOf(numL);
                    final int sNum = numL.equals("-1")?0:Integer.valueOf(numS);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String[] result = business.saleNumUpdate(date, lNum, sNum, bNum, cityN, true);
                            Message msg = new Message();
                            msg.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putStringArray("result", result);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            }
        });
    }

    private void enableButton() {
        button.setEnabled(true);
        button.setBackgroundColor(getResources().getColor(R.color.dodger_blue));
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_coner));
        button.setText("COMMIT");
    }
    private void clear() {
        lnum.setText("");
        bnum.setText("");
        snum.setText("");
        city.setText("");
        enableButton();
    }
}
