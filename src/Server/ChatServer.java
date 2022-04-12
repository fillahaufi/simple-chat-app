package Server;

public class ChatServer {
    public static void main(String[] args) {
        ThreadServer ts = new ThreadServer();
        ts.start();
        System.out.println("Server Started");
        System.out.println("Waiting for connections....");
    }
}
