package com.example.michael.imclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private EditText friend;
    private TextView chat;
    private EditText msg;
    private Button send;
    private static ChatActivity instance;

    private static StringBuilder sb = new StringBuilder();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message m) {
            switch (m.what) {
                case 0:
                    String data = (String) m.obj;
                    String[] strs = data.trim().split("#");
                    String s = msg.getText().toString();
                    sb.append("\n" + strs[1] + ":" + strs[2]);
                    chat.setText(sb.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        friend = (EditText) findViewById(R.id.editText_yourfriend);
        chat = (TextView) findViewById(R.id.textView_chat);
        chat.setMovementMethod(ScrollingMovementMethod.getInstance());
        msg = (EditText) findViewById(R.id.editText_msg);
        send = (Button) findViewById(R.id.button_send);
        instance = this;
    }

    public Handler getHandler() {
        return handler;
    }

    public static ChatActivity getInstance() {
        return instance;
    }
}
