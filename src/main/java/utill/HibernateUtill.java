package utill;

import modle.entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtill {

    private static SessionFactory sessionFactory = crateSessionFactory();

    private static SessionFactory crateSessionFactory(){
        Configuration config = new Configuration();

        config.addAnnotatedClass(CustomerEntity.class);


        config.configure();

        return config.buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
