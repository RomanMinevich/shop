package mate.academy.internetshop.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger log = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Session session = null;
        Transaction transaction = null;
        Long id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            id = (Long) session.save(order);
            order.setId(id);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Couldn't create an order for a user with id"
                    + order.getUser().getId(), exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setId(id);
        return order;
    }

    @Override
    public Order get(Long id) {
        Order order = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            order = session.get(Order.class, id);
        } catch (HibernateException exception) {
            log.error("Couldn't get an order with id " + id, exception);
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
                    + order.getUser().getId(), exception);
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
            log.error("Couldn't delete an order with id " + id, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where user.id = :userId");
            query.setParameter("userId", userId);
            orders = query.list();
        } catch (HibernateException exception) {
            log.error("Couldn't get all orders", exception);
        }
        return orders;
    }
}
