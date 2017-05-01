package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class ReadThread implements Runnable {
    private Socket socket;

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
            } catch (Exception e) {
                flag = false;
                System.out.println("ReadThread:客户端读进程错误！");
                e.printStackTrace();
            }
        }






    }
}
