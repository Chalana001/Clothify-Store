package Repository;

import modle.entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.HibernateUtill;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository{

    private Session session = HibernateUtill.getSession();

    @Override
    public List<CustomerEntity> getAllCustomer() {
        return session.createQuery("From CustomerEntity",CustomerEntity.class).list();
    }

    @Override
    public CustomerEntity getLastCustomerId() {
        return session.createQuery("FROM CustomerEntity o ORDER BY o.customerId DESC", CustomerEntity.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public void addCustomer(CustomerEntity customerEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(customerEntity);
        transaction.commit();
    }

    @Override
    public CustomerEntity getCustomerById(String text) {
        return session.find(CustomerEntity.class, text);
    }

    @Override
    public List<CustomerEntity> searchCustomerByNameSearch(String newText) {
//        if (newText == null || newText == " ") {
//            return List.of();
//        }
        String hql = "FROM CustomerEntity i " + "WHERE LOWER(i.name) LIKE LOWER(:name) " + "ORDER BY i.customerId ASC";

        Query<CustomerEntity> query = session.createQuery(hql, CustomerEntity.class);
        query.setParameter("name", newText.trim() + "%");
        //query.setMaxResults(3);
        return query.list();
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        Transaction tx = session.beginTransaction();
        session.merge(customerEntity);
        tx.commit();
    }

    @Override
    public void deleteCustomer(String id) {
        Transaction tx = session.beginTransaction();
        session.remove(session.find(CustomerEntity.class,id));
        tx.commit();
    }
}
