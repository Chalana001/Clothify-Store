package Repository;

import modle.entity.EmployeeEntity;
import modle.entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.HibernateUtill;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    Session session = HibernateUtill.getSession();
    @Override
    public void addEmployee(EmployeeEntity employeeEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(employeeEntity);
        transaction.commit();
    }

    @Override
    public EmployeeEntity getLastEmployeeId() {
        return session.createQuery("FROM EmployeeEntity o ORDER BY o.EId DESC", EmployeeEntity.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public void deleteEmployee(String eId) {
        Transaction tx = session.beginTransaction();
        session.remove(session.find(EmployeeEntity.class,eId));
        tx.commit();
    }

    @Override
    public void updateEmployee(EmployeeEntity employeeEntity) {
        Transaction tx = session.beginTransaction();
        session.merge(employeeEntity);
        tx.commit();
    }

    @Override
    public List<EmployeeEntity> getAllEmployee() {
        return session.createQuery("FROM EmployeeEntity",EmployeeEntity.class).list();
    }

    @Override
    public EmployeeEntity getEmployeeById(String text) {
        return session.find(EmployeeEntity.class, text);
    }

    @Override
    public List<EmployeeEntity> searchEmployeeByName(String newText) {
        String hql = "FROM EmployeeEntity i " + "WHERE LOWER(i.EName) LIKE LOWER(:EName) " + "ORDER BY i.EId ASC";

        Query<EmployeeEntity> query = session.createQuery(hql, EmployeeEntity.class);
        query.setParameter("EName", newText.trim() + "%");
        //query.setMaxResults(3);
        return query.list();
    }
}
