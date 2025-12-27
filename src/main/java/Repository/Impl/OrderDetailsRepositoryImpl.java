package Repository.Impl;


import Repository.OrderDetailsRepository;
import modle.entity.OrderDetailsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utill.HibernateUtill;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    Session session = HibernateUtill.getSession();

    @Override
    public void addOrderDetails(OrderDetailsEntity orderDetailsEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(orderDetailsEntity);
        transaction.commit();
    }
}
