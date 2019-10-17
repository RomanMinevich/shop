package mate.academy.internetshop.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User create(User user) {
        Long id = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            id = (Long) session.save(user);
            user.setId(id);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't create a user");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(id);
    }

    @Override
    public User get(Long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (HibernateException exception) {
            log.error("Couldn't get user with id " + id);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't update a user");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return get(user.getId());
    }

    @Override
    public User delete(Long id) {
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
            log.error("Couldn't delete a user with id " + id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public User login(String phoneNumber) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(
                    "from User where phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            user = (User)query.uniqueResult();
        } catch (HibernateException exception) {
            log.error("Couldn't get id for user with phone number " + phoneNumber);
        }
        return user;
    }

    @Override
    public User getByToken(String token) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(
                    "from User where token = :token");
            query.setParameter("token", token);
            Iterator iterator = query.iterate();
            if (iterator.hasNext()) {
                user = (User)iterator.next();
            }
        } catch (HibernateException exception) {
            log.error("Couldn't get user with token " + token);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createQuery("from User").list();
        } catch (HibernateException exception) {
            log.error("Couldn't get all users");
        }
        return users;
    }
}
