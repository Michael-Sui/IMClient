package client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Michael on 2017/4/16.
 */
public class Client {
    private final String IP;
    private final int PORT;
    private boolean isLogin;
    private static Client instance;

    public Client() {
        IP = "10.0.2.2";
        PORT = 10001;
        isLogin = false;
        Client.instance = this;
        try {
            Socket socket = new Socket(IP, PORT);
            new Thread(new ReadThread(socket)).start();
            new Thread(new WriteThread(socket)).start();
        } catch (IOException e) {
            System.out.println("Client:客户端错误！");
            e.printStackTrace();
        }
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public static Client getInstance() {
        return Client.instance;
    }
}
