package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Service implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic
    private String name;
    private String description;
    private double price;
    private boolean active;
    
    @ManyToMany
    private List<Package> packages;
    
    @ManyToMany
    private List<Sale> sales;
    
    @Temporal(TemporalType.DATE)
    private Date ServiceDate;

    //CONSTRUCTORS
    public Service() {
    }

    public Service(String name, String description, double price, Date ServiceDate) {
        this.name = name;
        this.description = description;
        this.price = Math.round(price * 100d) / 100d;
        this.active = true;
        this.ServiceDate = ServiceDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.round(price * 100d) / 100d;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packageList) {
        this.packages = packageList;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> saleList) {
        this.sales = saleList;
    }

    public Date getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Date ServiceDate) {
        this.ServiceDate = ServiceDate;
    }

    //OTHER METHODS
    public String getStrPackages(){
        String str = "";
        for(Package p: packages){
            if(p.isActive()){
                str += p.getId() + ". ";
            }
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
    
    public void addPackage(Package p){
        packages.add(p);
    }
}
