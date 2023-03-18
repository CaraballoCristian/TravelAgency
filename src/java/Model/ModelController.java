package Model;

import Entity.*;
import Entity.Package;
import Model.exceptions.NonexistentEntityException;
import java.util.List;

public class ModelController {
     
    CustomerJpaController customerJPA = new CustomerJpaController();
    EmployeeJpaController employeeJPA = new EmployeeJpaController();
    SaleJpaController saleJPA = new SaleJpaController();
    ServiceJpaController serviceJPA = new ServiceJpaController();
    UserJpaController userJPA = new UserJpaController();
    PackageJpaController packageJPA = new PackageJpaController();
    
    //--------------------CUSTOMER-------------------------
    //create
    public void createCustomer(Customer customer){
        customerJPA.create(customer);
    }
    //read
    public List<Customer> readCustomers(){
        return customerJPA.findCustomerEntities();
    }
    public Customer readCustomer(long idCustomer){
        return customerJPA.findCustomer(idCustomer);
    }
    public Customer readCustomer(Customer customer){
        return customerJPA.findCustomer(customer.getId());
    }
    //update
    public void updateCustomer(Customer customer) throws Exception{
        customerJPA.edit(customer);
    }
    //delete
    public void deleteCustomer(Customer customer) throws NonexistentEntityException{
        customerJPA.destroy(customer.getId());
    }
    public void deleteCustomer(long idCustomer) throws NonexistentEntityException{
        customerJPA.destroy(idCustomer);
    }
    
    //--------------------EMPLOYEE-------------------------
    //create
    public void createEmployee(Employee employee){
        employeeJPA.create(employee);
    }
    //read
    public List<Employee> readEmployees(){
        return employeeJPA.findEmployeeEntities();
    }
    public Employee readEmployee(long idEmployee){
        return employeeJPA.findEmployee(idEmployee);
    }
    public Employee readEmployee(Employee employee){
        return employeeJPA.findEmployee(employee.getId());
    }
    //update
    public void updateEmployee(Employee employee) throws Exception{
        employeeJPA.edit(employee);
    }
    //delete
    public void deleteEmployee(Employee employee) throws NonexistentEntityException{
        employeeJPA.destroy(employee.getId());
    }
    public void deleteEmployee(long idEmployee) throws NonexistentEntityException{
        employeeJPA.destroy(idEmployee);
    }
    
    //----------------------SALE---------------------------
    //create
    public void createSale(Sale sale){
        saleJPA.create(sale);
    }
    //read
    public List<Sale> readSales(){
        return saleJPA.findSaleEntities();
    }
    public Sale readSale(long idSale){
        return saleJPA.findSale(idSale);
    }
    public Sale readSale(Sale sale){
        return saleJPA.findSale(sale.getId());
    }
    //update
    public void updateSale(Sale sale) throws Exception{
        saleJPA.edit(sale);
    }
    //delete
    public void deleteSale(Sale sale) throws NonexistentEntityException{
        saleJPA.destroy(sale.getId());
    }
    public void deleteSale(int idSale) throws NonexistentEntityException{
        saleJPA.destroy(idSale);
    }
    
//----------------------SERVICE--------------------------
    //create
    public void createService(Service service){
        serviceJPA.create(service);
    }
    //read
    public List<Service> readServices(){
        return serviceJPA.findServiceEntities();
    }
    public Service readService(long idService){
        return serviceJPA.findService(idService);
    }
    public Service readService(Service service){
        return serviceJPA.findService(service.getId());
    }
    //update
    public void updateService(Service service) throws Exception{
        serviceJPA.edit(service);
    }
    //delete
    public void deleteService(Service service) throws NonexistentEntityException{
        serviceJPA.destroy(service.getId());
    }
    public void deleteService(long idService) throws NonexistentEntityException{
        serviceJPA.destroy(idService);
    }
    
    //-----------------------USER---------------------------
    //create
    public void createUser(User user){
        userJPA.create(user);
    }
    //read
    public List<User> readUsers(){
        return userJPA.findUserEntities();
    }
    public User readUser(long idUser){
        return userJPA.findUser(idUser);
    }
    public User readUser(User user){
        return userJPA.findUser(user.getId());
    }
    //update
    public void updateUser(User user) throws Exception{
        userJPA.edit(user);
    }
    //delete
    public void deleteUser(User user) throws NonexistentEntityException{
        userJPA.destroy(user.getId());
    }
    public void deleteUser(long idUser) throws NonexistentEntityException{
        userJPA.destroy(idUser);
    }
    
    //----------------------PACKAGE--------------------------
    //create
    public void createPackage(Package pack){
        packageJPA.create(pack);
    }
    //read
    public List<Package> readPackages(){
        return packageJPA.findPackageEntities();
    }
    public Package readPackage(long idPackage){
        return packageJPA.findPackage(idPackage);
    }
    public Package readPackage(Package service){
        return packageJPA.findPackage(service.getId());
    }
    //update
    public void updatePackage(Package pack) throws Exception{
        packageJPA.edit(pack);
    }
    //delete
    public void deletePackage(Package pack) throws NonexistentEntityException{
        packageJPA.destroy(pack.getId());
    }
    public void deletePackage(long idPackage) throws NonexistentEntityException{
        packageJPA.destroy(idPackage);
    }
    
}
