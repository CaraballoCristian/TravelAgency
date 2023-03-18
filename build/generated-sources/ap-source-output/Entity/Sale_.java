package Entity;

import Entity.Customer;
import Entity.Employee;
import Entity.Package;
import Entity.Service;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-16T15:25:22")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile SingularAttribute<Sale, Date> date;
    public static volatile SingularAttribute<Sale, Double> price;
    public static volatile SingularAttribute<Sale, String> paymentMethod;
    public static volatile SingularAttribute<Sale, Boolean> active;
    public static volatile SingularAttribute<Sale, Long> id;
    public static volatile ListAttribute<Sale, Service> services;
    public static volatile SingularAttribute<Sale, Employee> employee;
    public static volatile ListAttribute<Sale, Package> packages;
    public static volatile SingularAttribute<Sale, Customer> customer;

}