package Entity;

import Entity.Sale;
import Entity.Service;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-16T15:25:22")
@StaticMetamodel(Package.class)
public class Package_ { 

    public static volatile SingularAttribute<Package, Double> price;
    public static volatile SingularAttribute<Package, String> name;
    public static volatile SingularAttribute<Package, Boolean> active;
    public static volatile SingularAttribute<Package, Long> id;
    public static volatile ListAttribute<Package, Service> services;
    public static volatile ListAttribute<Package, Sale> sales;

}