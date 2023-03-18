package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Package implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String name;
    private double price;
    private boolean active;
    
    @ManyToMany
    private List<Service> services;
    
    @ManyToMany
    private List<Sale> sales;

    //CONSTRUCTORS
    public Package() {
        
    }

    public Package(String name, List<Service> services) {
        this.name = name;
        this.active = true;
        this.services = services;
        
        double p = 0;
        for(Service s: services){
            p += s.getPrice();
        }
        this.price = (Math.round((p - (p * 0.1)) * 100d) / 100d);
    }

    //GETTERS & SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        double p = 0;
        for(Service s: services){
            p += s.getPrice();
        }
        this.price = (Math.round((p - (p * 0.1)) * 100d) / 100d);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> serviceList) {
        this.services = serviceList;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> saleList) {
        this.sales = saleList;
    }
    
    //OTHER METHODS
    public String getStrServices(){
        String str = "";
        for(Service s: services){
            str += s.getId() + ". ";
        }
        if(!str.equals("")) return str;
        return "-";
    }
   
    public String getStrServicesNames(){
        String str = "";
        for(Service s: services){
            str += s.getName() + ", ";
        }
        if(!str.equals("")) return str.substring(0, str.length() - 2);
        return "-";
    }
   
    public String getStrSales(){
        String str = "";
        for(Sale s: sales){
            str += s.getId() + ". ";
        }
        if(!str.equals("")) return str;
        return "-";
    }
    
    public long getSalesCount(){
        return sales.size();
    }
    
    public void addSale(Sale s){
        sales.add(s);
    }
    
    public void addService(Service s){
        services.add(s);
    }
}
