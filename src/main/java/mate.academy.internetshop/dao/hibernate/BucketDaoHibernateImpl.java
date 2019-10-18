package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static final Logger log = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        Session session = null;
        Transaction transaction = null;
        Long id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            id = (Long) session.save(bucket);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't create a bucket", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setId(id);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        Bucket bucket = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bucket = session.get(Bucket.class, id);
        } catch (HibernateException exception) {
            log.error("Couldn't get a bucket with id " + id, exception);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't update a bucket", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            session.delete(get(id));
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't delete a bucket with id " + id, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
