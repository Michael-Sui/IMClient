package com.example.michael.imclient;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import client.Client;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText pwd;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(this);
        Button signup = (Button) findViewById(R.id.button_signup);
        signup.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editText_name);
        pwd = (EditText) findViewById(R.id.editText_pwd);

        clear();
    }

    @Override
    public void onClick(View v) {
        String str_name = name.getText().toString();
        String str_pwd = pwd.getText().toString();
        if (str_name.equals("") || str_pwd.equals("")) {
            Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        switch (v.getId()) {
            case R.id.button_login:
                new Thread(() -> {
                    try {
                        client = new Client("logIn", str_name, str_pwd);
                        Thread.sleep(3000);
                        if (client.getIsLogin()) {
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, ChatActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case R.id.button_signup:
                new Thread(() -> {
                    try {
                        client = new Client("signUp", str_name, str_pwd);
                        Thread.sleep(3000);
                        if (client.getIsLogin()) {
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, ChatActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    public void clear() {
        name.setText("");
        pwd.setText("");
    }
}
