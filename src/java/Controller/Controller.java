package Controller;

import Entity.Customer;
import Entity.Employee;
import Entity.Service;
import Entity.Package;
import Entity.Sale;
import Entity.User;
import Model.ModelController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private final ModelController model = new ModelController();
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Sale> sales;
    
    private static Controller control = null;
    
    //BASIC METHODS
    public static  Controller getInstance(){
        if(control == null) control = new Controller();
        
        return control;
    }
          
    //LOGIN
    public Employee validateUser(String user, String pass) {

        if (user.equals("admin") && pass.equals("admin")){
            Employee e = new Employee();
            e.setName("admin");
            e.setLastName("");
            
            User u = new User();
            u.setAdmin(true);
            e.setUser(u);
            
            return e;
            
        }else {
            employees = model.readEmployees();
            
            if(employees != null && !employees.isEmpty()){
                for(Employee e: employees){
                    String username = e.getUser().getUsername();
                    String password = e.getUser().getPassword();
                    String passAttempt = e.getUser().toMD5(pass);
                    
                    if(e.isActive() && username.equals(user) && password.equals(passAttempt)){
                        return e;
                    }
                }
            }
        }
        
        return null;
    }
    
    //CUSTOMER
    public List<Customer> getCustomers(){
        return model.readCustomers();
    }
    
    public Customer getCustomerByDni(long dni){

      customers = model.readCustomers();

      for(Customer c: customers){
          if(c.getDni() == dni){
              return c;
          }
      }
      return null;
  }
    
    public List<Customer> getActiveCustomers(){
        List<Customer> custs = model.readCustomers();
        List<Customer> active = new ArrayList();
        
        for(Customer c: custs){
            if(c.isActive()) active.add(c);
        }
        
        return active;
    }
    
    public Customer getCustomer(long id){
        return model.readCustomer(id);
    }
    
    public void createCustomer(String name, String lastName, long dni, String phone, String email, String nationality, String address, String birthdate) throws ParseException, Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dt = sdf.parse(birthdate);
        
        Customer cus = new Customer(dni, name, lastName, nationality, phone, email, address, dt);
                
        model.createCustomer(cus);
    }
     
    public void deleteCustomer(long id) throws Exception{
        Customer c = model.readCustomer(id);
        
        c.setActive(false);
        
        model.updateCustomer(c);
    }
    
    public void editCustomer(long id, String name, String lastName, long dni, String phone, String email, String nationality, String address, String birthdate) throws ParseException, Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dt = null;
        if(!birthdate.isEmpty()) dt = sdf.parse(birthdate);
        
        Customer cus = model.readCustomer(id);
        cus.setDni(dni);
        cus.setName(name);
        cus.setLastName(lastName);
        cus.setNationality(nationality);
        cus.setPhone(phone);
        cus.setEmail(email);
        cus.setAddress(address);
        cus.setBirthdate(dt);
        
        model.updateCustomer(cus);
    }

    
    //EMPLOYEE
    public List<Employee> getEmployees(){
        return model.readEmployees();
    }
    
    public List<Employee> getActiveEmployees(){
        List<Employee> emps = model.readEmployees();
        List<Employee> active = new ArrayList();
        
        for(Employee e: emps){
            if(e.isActive()) active.add(e);
        }
        
        return active;
    }
    
    public Employee getEmployee(long id){
        return model.readEmployee(id);
    }
    
    public void createEmployee(String name, String lastName, long dni, String phone, String email, String nationality, String address, String birthdate, double salary, String hireDate, String endDate, String username, String password, String admin) throws ParseException, Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date dt = null;
        if(!birthdate.isEmpty()) dt = sdf.parse(birthdate);
        
        Date hdt = null;
        if(!hireDate.isEmpty()) hdt = sdf.parse(birthdate);
        
        
        boolean ad = false;
        if(admin.equals("ADMIN")) ad = true;
                
        User user = new User(username, password, true, ad);
        
        model.createUser(user);
        
        Employee emp = new Employee(salary, user, dni, name, lastName, nationality, phone, email, address, dt, hdt);
                
        model.createEmployee(emp);
        
    }
     
    public void deleteEmployee(long id) throws Exception{
        
        Employee emp = model.readEmployee(id);
        
        User u = emp.getUser();
        u.setActive(false);
        
        emp.setActive(false);
    
        model.updateUser(u);
        
        model.updateEmployee(emp);
        
    }
    
    public void editEmployee(long id, String name, String lastName, long dni, String phone, String email, String nationality, String address, String birthdate, double salary, String hireDate, String username, String password, String admin) throws ParseException, Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dt = null;
        if(!birthdate.isEmpty()) dt = sdf.parse(birthdate);
        
        Date hdt = null;
        if(!hireDate.isEmpty()) hdt = sdf.parse(birthdate);
                
        Employee emp = model.readEmployee(id);
        
        emp.setDni(dni);
        emp.setName(name);
        emp.setLastName(lastName);
        emp.setNationality(nationality);
        emp.setPhone(phone);
        emp.setEmail(email);
        emp.setAddress(address);
        emp.setBirthdate(dt);
        emp.setSalary(salary);
        emp.setHireDate(hdt);
        
        boolean ad = false;
        if(admin.equals("ADMIN")) ad = true;
        
        User user = emp.getUser();
        user.setAdmin(ad);
        user.setUsername(username);
        if(!password.equals("")) user.setPassword(password);
        
        emp.setUser(user);
        
        model.updateUser(user);
        
        model.updateEmployee(emp);
    }
    
    
    //SERVICE
    public List<Service> getServices(){
      return model.readServices();
    }
    
    public List<Service> getActiveServices(){
        
        List<Service> servs = model.readServices();
        List<Service> active = new ArrayList();
        
        for(Service s: servs){
            if(s.isActive()) active.add(s);
        }
        
        return active;
    }
    
    public Service getService(long id){
        return model.readService(id);
    }
    
    public void createService(String name, String description, double price, String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dt = null;
        if(!date.isEmpty()) dt = sdf.parse(date);
        
        Service serv = new Service(name, description, price, dt);
        
        model.createService(serv);
    }
    
    public void deleteService(long id) throws Exception{             
        Service serv = model.readService(id);
        
        List<Package> packs = serv.getPackages();
        
        if(packs != null && !packs.isEmpty()){
            for(Package p: packs){
                p.setActive(false);
                model.updatePackage(p);
            }
        }
            
        serv.setActive(false);
        
        model.updateService(serv);
    }
    
    public void editService(long id, String name, String description, double price, String date) throws ParseException, Exception{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date dt = null;
        if(!date.isEmpty()) dt = sdf.parse(date);
        
        Service serv = model.readService(id);
        
        serv.setName(name);
        serv.setDescription(description);
        serv.setPrice(price);
        serv.setServiceDate(dt);
        
        model.updateService(serv);
        
        List<Package> packs = model.readPackages();
        for(Package p: packs){
            p.setPrice();
            model.updatePackage(p);
        }
    }
    
    
    //PACKAGE
    public List<Package> getPackages(){
        return model.readPackages();
    }
    
    public List<Package> getActivePackages(){
        
        List<Package> packs = model.readPackages();
        List<Package> active = new ArrayList();
        
        for(Package p: packs){
            if(p.isActive()) active.add(p);
        }
        
        return active;
    }
    
    public Package getPackage(long id){
        return model.readPackage(id);
    }
    
    public void createPackage(String name, String[] services) throws Exception{
        
        List<Service> servs = new ArrayList();
                
        for(String s: services){
            Service serv = model.readService(Long.parseLong(s));
                       
            servs.add(serv);
        }
        
        Package pack = new Package(name, servs);
                
        model.createPackage(pack);
        
        for(Service s: servs){
            s.addPackage(pack);
            model.updateService(s);
        }
    }
    
    public void deletePackage(long id) throws Exception{
       Package p = model.readPackage(id);
       
       p.setActive(false);
       
       model.updatePackage(p);
    }
    
    public void editPackage(long id, String name, String[] services) throws Exception{
        
        List<Service> servs = new ArrayList();
        
        for(String s: services){
            long servId = Long.parseLong(s);
            Service serv = model.readService(servId);
            servs.add(serv);
        }
        
        Package pack = model.readPackage(id);
        pack.setName(name);
        pack.setServices(servs);
        pack.setPrice();
        
        model.updatePackage(pack);
    }
    
    
    //SALES
    public List<Sale> getSales(){
        return model.readSales();
    }
    
    public List<Sale> getActiveSales(){
        List<Sale> sls = model.readSales();
        List<Sale> active = new ArrayList();
        
        for(Sale s: sls){
            if(s.isActive()) active.add(s);
        }
        
        return active;
    }
    
    public Sale getSale(long id){
        return model.readSale(id);
    }
    
    public void createSale(long cusId, long empId, String[] packages, String[] services, String paymentMethod) throws Exception{
        
        Customer cus = model.readCustomer( cusId);
      
        Employee emp = model.readEmployee( empId);
        
        double price = 0;
       
        List<Package> packs = new ArrayList();
        if(packages != null && packages.length > 0){
            for(String p: packages){
                Package pack = model.readPackage(Long.parseLong(p));
                packs.add(pack);

                price += pack.getPrice();
            } 
        }
        
        List<Service> servs = new ArrayList();
        if(services != null && services.length > 0){
            for(String s: services){
                Service serv = model.readService(Long.parseLong(s));
                servs.add(serv);

                price += serv.getPrice();
            }
        }
        
        switch(paymentMethod){
            case "CASH":     price = price - (price * 0.05); break;
            case "DEBIT":    break;
            case "CREDIT":   price = price + (price * 0.09); break;
            case "VIRTUAL":  break;
            case "TRANSFER": price = price + (price * 0.03); break;
            default: break;
        }
        
        Sale newSale = new Sale(price, paymentMethod, cus, emp, packs, servs);
        model.createSale(newSale);
        
        //Adding sale to all related entities
        cus.addSale(newSale);
        model.updateCustomer(cus);
        
        emp.addSale(newSale);
        model.updateEmployee(emp);
        
        if(!servs.isEmpty()){
            for(Service s: servs){
                s.addSale(newSale);
                model.updateService(s);
            }
        }
        
        if(!packs.isEmpty()){
            for(Package p: packs){
                p.addSale(newSale);
                model.updatePackage(p);
            }
        }
    }
    
    public void editSale(long id, long cusId, long empId, String[] packages, String[] services, String paymentMethod) throws Exception{
      
        Sale sale = model.readSale(id);
        
        Customer cus = model.readCustomer( cusId);
      
        Employee emp = model.readEmployee( empId);
        
        double price = 0;
       
        List<Package> packs = new ArrayList();
        if(packages != null){
            for(String p: packages){
                Package pack = model.readPackage(Long.parseLong(p));
                packs.add(pack);

                price += pack.getPrice();
            } 
        }
        
        List<Service> servs = new ArrayList();
        if(services != null){
            for(String s: services){
                Service serv = model.readService(Long.parseLong(s));
                servs.add(serv);

                price += serv.getPrice();
            }
        }
        
        switch(paymentMethod){
            case "CASH":     price = price - (price * 0.05); break;
            case "DEBIT":    break;
            case "CREDIT":   price = price + (price * 0.09); break;
            case "VIRTUAL":  break;
            case "TRANSFER": price = price + (price * 0.03); break;
            default: break;
        }
      
        sale.setPrice(price);
        sale.setCustomer(cus);
        sale.setEmployee(emp);
        sale.setPackages(packs);
        sale.setServices(servs);
        sale.setPaymentMethod(paymentMethod);
        
        model.updateSale(sale);
    }
     
    public void deleteSale(long id) throws Exception{
         Sale s = model.readSale(id);
         
         s.setActive(false);
         
         model.updateSale(s);
    }
     
    
    //EARNS
    public double getFilteredAmount(String d1, String d2) throws ParseException{                       
        
        sales = model.readSales();
        
        double total = 0;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date1 = null;
        Date date2 = null;
        
        if(!d1.isEmpty()) date1 = sdf.parse(d1);
        if(!d2.isEmpty()) date2 = sdf.parse(d2);
                
        if(!d1.isEmpty() && !d2.isEmpty()){
            for(Sale s: sales){
                if(s.isActive()){
                    if(s.getDate().compareTo(date1) >= 0 && s.getDate().compareTo(date2) <= 0){
                        total += s.getPrice();
                    }   
                }
            }           
         
        }else if(!d1.isEmpty() && d2.isEmpty()){
            for(Sale s: sales){
                if(s.isActive()){
                    if(s.getDate().after(date1)){
                        total += s.getPrice();
                    }
                }
            }   
            
        }else if(d1.isEmpty() && !d2.isEmpty()){
            for(Sale s: sales){
                if(s.isActive()){
                    if(s.getDate().before(date2)){
                        total += s.getPrice();
                    }
                }
            }
            
        }else{
            for(Sale s: sales){
                if(s.isActive()){
                    total += s.getPrice();
                }
            }            
        }
        total = Math.round(total * 100d) / 100d;
        return total;
    }
    
    public double[] getEarns(){
        sales = model.readSales();
        double[] earns = {0, 0, 0, 0};
        
        SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM/yyyy");
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        
        for(Sale s: sales){
            if(s.isActive()){
                String saleFullDate = sdfFull.format(s.getDate());
                String saleMonthDate = sdfMonth.format(s.getDate());
                String saleYearDate = sdfYear.format(s.getDate());

                String todayFull = sdfFull.format(new Date());
                String todayMonth = sdfMonth.format(new Date());
                String todayYear = sdfYear.format(new Date());

                //ALL
                earns[3] += s.getPrice();

                //THIS YEAR
                if(saleYearDate.equals(todayYear)){
                    earns[2] += s.getPrice();

                    //THIS MONTH
                    if(saleMonthDate.equals(todayMonth)){
                        earns[1] += s.getPrice();

                        //TODAY
                        if(saleFullDate.equals(todayFull)){
                            earns[0] += s.getPrice();
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < earns.length; i++){
            earns[i] = Math.round(earns[i] * 100d) / 100d;
        }
        
        return earns;
    }
    
}
