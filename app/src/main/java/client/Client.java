package client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class Client {
    private static final String IP = "10.0.2.2";
    private static final int PORT = 10001;
    private static boolean isLogin;

    public Client() {
        isLogin = false;
        try {
            Socket socket = new Socket(IP, PORT);
            new Thread(new ReadThread(socket)).start();
            new Thread(new WriteThread(socket)).start();
        } catch (IOException e) {
            System.out.println("Client:客户端错误！");
            e.printStackTrace();
        }
    }

    public static void setIsLogin(boolean isLogin) {
        Client.isLogin = isLogin;
    }

    public static boolean getIsLogin() {
        return Client.isLogin;
    }
}
