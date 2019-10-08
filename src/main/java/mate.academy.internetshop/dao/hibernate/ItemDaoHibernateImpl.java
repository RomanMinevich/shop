package mate.academy.internetshop.dao.hibernate;

import java.util.List;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static final Logger log = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item create(Item item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            Long id = (Long) session.save(item);
            item.setId(id);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Item.class, id);
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
