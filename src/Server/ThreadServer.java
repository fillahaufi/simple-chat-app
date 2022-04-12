package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import objects.Message;

public class ThreadServer extends Thread {
    private Hashtable<String, ThreadClient> ClientList;
    private ServerSocket server;

    public ThreadServer() {
        try {
            this.ClientList = new Hashtable<String, ThreadClient>();
            this.server = new ServerSocket(6666);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            try {
                Socket socket = this.server.accept();
                System.out.println("Client Connected");
                ThreadClient tc = new ThreadClient(socket, this);
                tc.start();

                String clientId = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
                this.ClientList.put(clientId, tc);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendToAll(Message msg) {
        Enumeration<String> e = this.ClientList.keys();

        while(e.hasMoreElements()) {
            String clientId = e.nextElement();
            ThreadClient tc = this.ClientList.get(clientId);

            tc.send(msg);
        }
    }
}
