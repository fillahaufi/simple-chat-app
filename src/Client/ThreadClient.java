package Client;

import java.io.IOException;
import java.io.ObjectInputStream;

import objects.Message;

public class ThreadClient extends Thread {
    private ObjectInputStream ois;
    
    public ThreadClient(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void run() {
        while(true) {
            try {
                Message msg = (Message) this.ois.readObject();
                System.out.println(msg.getSender() + ": " + msg.getText());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
