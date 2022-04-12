package Server;

public class ChatServer {
    public static void main(String[] args) {
        ThreadServer ts = new ThreadServer();
        ts.start();
        System.out.println("Server started on port 6666");
        System.out.println("Waiting for clients connections....");
    }
}
