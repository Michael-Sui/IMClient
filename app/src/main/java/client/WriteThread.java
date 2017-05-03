package client;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class WriteThread implements Runnable {
    private Socket socket;
    private String type;
    private String name;
    private String msg;

    public WriteThread(Socket sockst, String type, String name, String msg) {
        this.socket = sockst;
        this.type = type;
        this.name = name;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            System.out.println(type + "#" + name + "#" + msg);
            output.writeUTF(type + "#" + name + "#" + msg);
            output.flush();
        } catch (Exception e) {
            System.out.println("WriteThread:客户端写进程错误！");
            e.printStackTrace();
        }
    }
}
