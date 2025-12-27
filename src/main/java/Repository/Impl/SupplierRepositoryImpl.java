package Repository.Impl;

import Repository.SupplierRepository;
import modle.entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.HibernateUtill;

import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {
    Session session = HibernateUtill.getSession();
    @Override
    public void addSupplier(SupplierEntity supplierEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(supplierEntity);
        transaction.commit();
    }

    @Override
    public SupplierEntity getLastSupplierId() {
        return session.createQuery("FROM SupplierEntity o ORDER BY o.SId DESC", SupplierEntity.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public void updateSupplier(SupplierEntity supplierEntity) {
        Transaction tx = session.beginTransaction();
        session.merge(supplierEntity);
        tx.commit();
    }

    @Override
    public void deleteSupplier(String sId) {
        Transaction tx = session.beginTransaction();
        session.remove(session.find(SupplierEntity.class,sId));
        tx.commit();
    }

    @Override
    public List<SupplierEntity> getAllSupplier() {
        return session.createQuery("FROM SupplierEntity",SupplierEntity.class).list();
    }

    @Override
    public SupplierEntity getSupplierById(String text) {
        return session.find(SupplierEntity.class, text);
    }

    @Override
    public List<SupplierEntity> searchSupplierByName(String newText) {
        String hql = "FROM SupplierEntity i " + "WHERE LOWER(i.SName) LIKE LOWER(:SName) " + "ORDER BY i.SId ASC";

        Query<SupplierEntity> query = session.createQuery(hql, SupplierEntity.class);
        query.setParameter("SName", newText.trim() + "%");
        //query.setMaxResults(3);
        return query.list();
    }
}
