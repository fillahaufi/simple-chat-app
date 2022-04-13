package objects;

import java.io.Serializable;

public class ClientList implements Serializable {
    private String id;
    private String username;
    
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setId(String id) {
        this.id = id;
    }
}
