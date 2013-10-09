package org.cidrz.webapp.dynasite.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HibernateUtil {


    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        /**
         * Commons Logging instance.
         */
        Log log = LogFactory.getFactory().getInstance(SessionFactory.class);

        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException ex) {
                throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
            } catch (AbstractMethodError ex) {
                log.error(ex);
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = getSessionFactory().openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null) {
            s.close();
        }
    }

}
