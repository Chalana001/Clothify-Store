package utill;

import modle.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtill {

    private static SessionFactory sessionFactory = crateSessionFactory();

    private static SessionFactory crateSessionFactory(){
        Configuration config = new Configuration();

        config.addAnnotatedClass(CustomerEntity.class);
        config.addAnnotatedClass(SupplierEntity.class);
        config.addAnnotatedClass(EmployeeEntity.class);
        config.addAnnotatedClass(ProductEntity.class);
        config.addAnnotatedClass(OrdersEntity.class);
        config.addAnnotatedClass(OrderDetailsEntity.class);
        config.addAnnotatedClass(UserEntity.class);


        config.configure();

        return config.buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
