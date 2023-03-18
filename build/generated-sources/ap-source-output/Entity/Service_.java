package Entity;

import Entity.Package;
import Entity.Sale;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-16T15:25:22")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, Date> ServiceDate;
    public static volatile SingularAttribute<Service, Double> price;
    public static volatile SingularAttribute<Service, String> name;
    public static volatile SingularAttribute<Service, String> description;
    public static volatile SingularAttribute<Service, Boolean> active;
    public static volatile SingularAttribute<Service, Long> id;
    public static volatile ListAttribute<Service, Package> packages;
    public static volatile ListAttribute<Service, Sale> sales;

}