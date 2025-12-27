package Repository.Impl;

import Repository.ProductRepository;
import modle.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utill.HibernateUtill;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    Session session = HibernateUtill.getSession();

    @Override
    public List<ProductEntity> getAllproduct() {
        return session.createQuery("FROM ProductEntity",ProductEntity.class).list();
    }

    @Override
    public void addProduct(ProductEntity productEntity) {
        Transaction transaction = session.beginTransaction();
        session.persist(productEntity);
        transaction.commit();
    }

    @Override
    public ProductEntity getLastProductId() {
        return session.createQuery("FROM ProductEntity o ORDER BY o.PId DESC", ProductEntity.class).setMaxResults(1).uniqueResult();
    }

    @Override
    public void updateProduct(ProductEntity productEntity) {
        Transaction tx = session.beginTransaction();
        session.merge(productEntity);
        tx.commit();
    }

    @Override
    public void deleteProduct(String pId) {
        Transaction tx = session.beginTransaction();
        session.remove(session.find(ProductEntity.class,pId));
        tx.commit();
    }

    @Override
    public ProductEntity getProductById(String text) {
        return session.find(ProductEntity.class, text);
    }

    @Override
    public List<ProductEntity> searchProductByName(String newText) {
        String hql = "FROM ProductEntity i " + "WHERE LOWER(i.PName) LIKE LOWER(:PName) " + "ORDER BY i.PId ASC";

        Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
        query.setParameter("PName", newText.trim() + "%");
        //query.setMaxResults(3);
        return query.list();
    }
}
