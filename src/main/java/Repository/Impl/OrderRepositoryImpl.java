package Repository.Impl;

import Repository.OrderRepository;
import modle.entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utill.HibernateUtill;

public class OrderRepositoryImpl implements OrderRepository {

    Session session = HibernateUtill.getSession();

    @Override
    public void addOrder(OrdersEntity ordersEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(ordersEntity);
        transaction.commit();
    }

    @Override
    public OrdersEntity getLastOrder() {
        return session.createQuery("FROM OrdersEntity o ORDER BY o.orderId DESC", OrdersEntity.class).setMaxResults(1).uniqueResult();
    }
}
