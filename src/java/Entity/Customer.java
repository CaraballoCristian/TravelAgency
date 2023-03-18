package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Customer extends Person implements Serializable {
    
    @Basic
    private boolean active;
    
    @OneToMany
    private List<Sale> sales;

    //CONSTRUCTORS
    public Customer() {
    }

    public Customer(long dni, String name, String lastName, String nationality, String phone, String email, String address, Date birthdate) {
        super(dni, name, lastName, nationality, phone, email, address, birthdate);
        this.active = true;
    }

    //GETTERS & SETTERS
   
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Sale> getSales() {
        return sales;
    }
    
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }   
    
    //OTHER METHODS
    public String getStrSales(){
        String str = "";
        for(Sale s: sales){
            str += s.getId() + ". ";
        }
        return str;
    }
    
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
   
    
}
