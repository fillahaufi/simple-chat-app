package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import objects.Message;

public class ThreadClient extends Thread {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ThreadServer threadServer;

    public ThreadClient(Socket socket, ThreadServer threadServer) {
        try {
            this.socket = socket;
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            this.threadServer = threadServer;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void run() {
        while(true) {
            try {
                Message msg = (Message) this.ois.readObject();

                this.threadServer.sendToAll(msg);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void send(Message msg) {
        try {
            this.oos.writeObject(msg);
            this.oos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
