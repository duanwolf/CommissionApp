package com.commission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView nameTxt;
    private TextView pswText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = (TextView) findViewById(R.id.phone);
        pswText = (TextView) findViewById(R.id.psw);
        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String psw = pswText.getText().toString();
                if (name.equals("") || psw.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, CommissionActivity.class);
                    i.putExtra("username", name);
                    i.putExtra("password", psw);
                    startActivity(i);
                }
            }
        });
    }
}
