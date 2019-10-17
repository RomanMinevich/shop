package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger log = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            Long id = (Long) session.save(order);
            order.setId(id);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't create an order for a user with id"
                    + order.getUser().getId());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public Order get(Long id) {
        Order order = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            order = session.get(Order.class, id);
        } catch (HibernateException exception) {
            log.error("Couldn't get an order with id " + id);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            session.update(order);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't update an order for a user with id "
                    + order.getUser().getId());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public Order delete(Long id) {
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
            log.error("Couldn't delete an order with id " + id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
