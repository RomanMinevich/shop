package mate.academy.internetshop.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Logger log = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception exception) {
            log.error("Couldn't build sessionFactory");
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
