package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    
    @Basic
    private long dni;
    private String name;
    private String lastName;
    private String nationality;
    private String phone;
    private String email;
    private String address;
    
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    //CONSTRUCTORS
    public Person() {
    }

    public Person(long dni, String name, String lastName, String nationality, String phone, String email, String address, Date birthdate) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
    }
  
    
    //GETTERS & SETTERS
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

}
