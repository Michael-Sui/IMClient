package client;

import com.example.michael.imclient.MainActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Michael on 2017/4/16.
 */
public class WriteThread implements Runnable {
    private Socket socket;

    public WriteThread(Socket sockst) {
        this.socket = sockst;
    }

    @Override
    public void run() {
        boolean flag = true;
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            String name = MainActivity.getStr_name();
            String pwd = MainActivity.getStr_pwd();
            output.writeUTF("logIn#" + name + "#" + pwd);
            output.flush();
            //TODO 本部分工作待完成。
        } catch (Exception e) {
            flag = false;
            System.out.println("WriteThread:客户端写进程错误！");
            e.printStackTrace();
        }
    }
}
