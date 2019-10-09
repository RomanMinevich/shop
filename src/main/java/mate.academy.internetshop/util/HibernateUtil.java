package mate.academy.internetshop.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Logger log = Logger.getLogger(HibernateUtil.class);

    private HibernateUtil() {

    }

    static  {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (ExceptionInInitializerError exception) {
            log.error("Couldn't build sessionFactory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
