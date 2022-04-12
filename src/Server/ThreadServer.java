package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import objects.Message;

public class ThreadServer extends Thread {
    private Hashtable<String, ThreadClient> ClientList;
    private String [] users;
    private ServerSocket server;

    public ThreadServer() {
        try {
            this.server = new ServerSocket(6666);
            this.ClientList = new Hashtable<String, ThreadClient>();
            this.users = new String[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            try {
                Socket socket = this.server.accept();
                System.out.println("Client connected from " + socket.getInetAddress().getHostAddress());
                ThreadClient tc = new ThreadClient(socket, this);
                tc.start();
                String clientId = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
                this.ClientList.put(clientId, tc);
                this.users = this.getUsers();
                System.out.println("Online users: " + this.users.length);
                for(String user : this.users) {
                    System.out.println("user " + user.split(":")[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getUsers() {
        Enumeration<String> e = this.ClientList.keys();
        String [] users = new String[this.ClientList.size()];
        int i = 0;
        while(e.hasMoreElements()) {
            users[i] = e.nextElement();
            i++;
        }
        return users;
    }

    public void sendToAll(Message msg) {
        Enumeration<String> e = this.ClientList.keys();

        while(e.hasMoreElements()) {
            String clientId = e.nextElement();
            ThreadClient tc = this.ClientList.get(clientId);
            // send online users to client
            tc.send(msg);
        }
    }
}
