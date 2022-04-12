package objects;
import java.io.Serializable;

public class Person implements Serializable {
    private String fullname;
    private int age;

    public String getFullname() {
        return fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
