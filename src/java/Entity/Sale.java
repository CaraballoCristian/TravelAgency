package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Sale implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private double price;
    private String paymentMethod;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Employee employee;
        
    @ManyToMany
    private List<Package> packages;

    @ManyToMany
    private List<Service> services;
    
    private boolean active;
    
    @Temporal(TemporalType.DATE)
    private Date date;

    //CONSTRUCTORS
    public Sale() {
    }

    public Sale(double price, String paymentMethod, Customer customer, Employee employee, List<Package> packages, List<Service> services) {
        this.price = Math.round(price * 100d) / 100d;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.employee = employee;
        this.packages = packages;
        this.services = services;
        this.date = new Date();
        this.active = true;
    }

    //GETTER & SETTERS
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.round(price * 100d) / 100d;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> Services) {
        this.services = Services;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    //OTHER METHODS
    public String getStrPackages(){
        String str = "";
        for(Package p: packages){
            str += p.getId() + ". ";
        }
        return str;
    }
    
    public String getStrServices(){
        String str = "";
        for(Service s: services){
            str += s.getId() + ". ";
        }
        return str;
    }
    
}
