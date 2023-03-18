package Entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.*;

@Entity
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String username;
    private String password;
    private boolean active;
    private boolean administrator;

    //CONSTRUCTORS
    public User() {
    }

    public User(String username, String password, boolean active, boolean administrator) {
        this.username = username;
        this.password = toMD5(password);
        this.active = active;
        this.administrator = administrator;
    }

    //GETTER & SETTER
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = toMD5(password);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return administrator;
    }

    public void setAdmin(boolean administrator) {
        this.administrator = administrator;
    }
    
    //Password encrypt
    public String toMD5(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes());
            BigInteger num = new BigInteger(1, encBytes);
            String encString = num.toString(16);
            while(encString.length() < 32){
                encString = "0" + encString;
            }
            return encString;
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
