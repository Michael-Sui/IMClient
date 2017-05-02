package com.example.michael.imclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import client.Client;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button signup;
    private EditText name;
    private EditText pwd;
    private static String str_name;
    private static String str_pwd;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(this);
        signup = (Button) findViewById(R.id.button_signup);
        signup.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editText_name);
        pwd = (EditText) findViewById(R.id.editText_pwd);

        clear();
    }

    @Override
    public void onClick(View v) {
        str_name = name.getText().toString();
        str_pwd = pwd.getText().toString();
        if (str_name.equals("") || str_pwd.equals("")) {
            return;
        }
        switch (v.getId()) {
            case R.id.button_login:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client = new Client();
                            Thread.sleep(3000);
                            if (!client.getIsLogin()) {
                                return;
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, ChatActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            case R.id.button_signup:
                //TODO 注册功能待完成！
                break;
            default:
                break;
        }
    }

    public void clear() {
        name.setText("");
        pwd.setText("");
    }

    public static String getStr_name() {
        return str_name;
    }

    public static String getStr_pwd() {
        return str_pwd;
    }
}
