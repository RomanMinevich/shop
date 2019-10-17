package mate.academy.internetshop.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();
    private static Logger log = Logger.getLogger(HibernateUtil.class);

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            sessionFactory =  new Configuration().configure().buildSessionFactory();
        } catch (Exception exception) {
            log.error("Couldn't build sessionFactory", exception);
            throw new RuntimeException(exception);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
