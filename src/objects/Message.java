package objects;
import java.io.Serializable;

public class Message implements Serializable {
    private String sender, text;

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
