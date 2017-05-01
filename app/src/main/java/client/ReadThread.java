package client;

import android.os.Handler;
import android.os.Message;
import android.widget.EditText;

import com.example.michael.imclient.ChatActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class ReadThread implements Runnable {
    private Socket socket;
    private Handler handler;
    private ChatActivity chatActivity;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String msg = input.readUTF();
                if (msg.equals("msg#Server#登陆成功")) {
                    Client.setIsLogin(true);
                }

                while (flag) {
                    msg = input.readUTF();
                    System.out.println("msg:" + msg);
                    chatActivity = ChatActivity.getInstance();
                    handler = chatActivity.getHandler();
                    Message m = new Message();
                    m.what = 0;
                    m.obj = msg;
                    handler.sendMessage(m);
                }
            } catch (Exception e) {
                flag = false;
                System.out.println("ReadThread:客户端读进程错误！");
                e.printStackTrace();
            }
        }






    }
}
