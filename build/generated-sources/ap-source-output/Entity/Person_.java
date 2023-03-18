package Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-16T15:25:22")
@StaticMetamodel(Person.class)
public abstract class Person_ { 

    public static volatile SingularAttribute<Person, String> lastName;
    public static volatile SingularAttribute<Person, String> address;
    public static volatile SingularAttribute<Person, Date> birthdate;
    public static volatile SingularAttribute<Person, String> nationality;
    public static volatile SingularAttribute<Person, String> phone;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, Long> dni;
    public static volatile SingularAttribute<Person, String> email;

}