package com.example.michael.imclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;

import client.Client;
import client.WriteThread;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText friend;
    private TextView chat;
    private EditText msg;
    private static ChatActivity instance;
    private Socket socket;

    private static StringBuilder sb = new StringBuilder();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message m) {
            switch (m.what) {
                case 0:
                    String data = (String) m.obj;
                    String[] strs = data.trim().split("#");
                    String s = msg.getText().toString();
                    sb.append("\n").append(strs[1]).append(":").append(strs[2]);
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
        Button send = (Button) findViewById(R.id.button_send);
        send.setOnClickListener(this);
        instance = this;
        Client client = Client.getInstance();
        socket = client.getSocket();

        clear();
    }

    public Handler getHandler() {
        return handler;
    }

    public static ChatActivity getInstance() {
        return instance;
    }

    public void clear() {
        friend.setText("");
        chat.setText("");
        msg.setText("");
    }

    @Override
    public void onClick(View v) {
        String str_friend = friend.getText().toString();
        String str_msg = msg.getText().toString();
        if (str_friend.equals("") || str_msg.equals("")) {
            return;
        }
        switch (v.getId()) {
            case R.id.button_send:
                new Thread(new WriteThread(socket, "msg", str_friend, str_msg)).start();
                msg.setText("");
                String s = msg.getText().toString();
                sb.append("\n" + "to:").append(str_friend).append(":").append(str_msg);
                chat.setText(sb.toString());
                break;
            default:
                break;
        }
    }
}
