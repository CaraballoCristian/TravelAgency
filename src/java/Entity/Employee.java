package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Employee extends Person implements Serializable {
       
    @Basic
    private double salary;
    private boolean active;
    
    @OneToOne
    private User user;
    
    @OneToMany
    private List<Sale> sales;
    
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;

    
    //CONSTRUCTORS
    public Employee() {
    }

    public Employee(double salary, User user, long dni, String name, String lastName, String nationality, String phone, String email, String address, Date birthdate, Date hireDate) {
        super(dni, name, lastName, nationality, phone, email, address, birthdate);
        this.salary = salary;
        this.user = user;
        this.active = true;
        this.hireDate = hireDate;
    }
    
    //GETTERS & SETTERS
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //OTHER METHODS
    public String getFullName(){
        return super.getName() + " " + super.getLastName();
    }
    
    public void addSale(Sale s){
        sales.add(s);
    }

    public void editSale(int i, Sale sale){
        sales.remove(i);
        sales.add(i, sale);
    }
    
    public String getStrSales(){
        String str = "";
        for(Sale s: sales){
            str += s.getId() + ". ";
        }
        return str;
    }
}
