package Client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import objects.Message;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 6666)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // ThreadClient ts = new ThreadClient(new ObjectInputStream(socket.getInputStream()));
            // ts.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Username : ");
            String username = reader.readLine();

            if(username != null) {
                ThreadClient ts = new ThreadClient(new ObjectInputStream(socket.getInputStream()));
                ts.start();
                while(true) {
                    System.out.println("Message : ");
                    String message = reader.readLine();
                    
                    Message msg = new Message();
                    msg.setSender(username);
                    msg.setText(message);
    
                    oos.writeObject(msg);
                    oos.flush();
                }
            }
            
        }
    }
}
