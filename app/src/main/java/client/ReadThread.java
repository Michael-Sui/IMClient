package client;

import android.os.Handler;
import android.os.Message;

import com.example.michael.imclient.ChatActivity;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class ReadThread implements Runnable {
    private Socket socket;
    private Handler handler;
    private ChatActivity chatActivity;
    private Client client;
    private boolean flag;

    public ReadThread(Socket socket) {
        this.socket = socket;
        client = Client.getInstance();
        flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String msg = input.readUTF();
                System.out.println(msg);
                if (msg.equals("msg#Server#SUCCESS")) {
                    client.setIsLogin(true);
                }

                while (flag) {
                    msg = input.readUTF();
                    System.out.println("msg:" + msg);
                    try {
                        Thread.sleep(6000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
