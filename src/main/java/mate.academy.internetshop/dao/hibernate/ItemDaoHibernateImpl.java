package mate.academy.internetshop.dao.hibernate;

import java.util.ArrayList;
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
            log.error("Couldn't create an item");
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        Item item = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            item = session.get(Item.class, id);
        } catch (Exception exception) {
            log.error("Couldn't get item with id " + id);
        }
        return item;
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
            log.error("Couldn't update an item");
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
            log.error("Couldn't delete an item with id " + id);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            items = session.createQuery("FROM Item").list();
        } catch (Exception exception) {
            log.error("Couldn't get all items");
        }
        return items;
    }
}
