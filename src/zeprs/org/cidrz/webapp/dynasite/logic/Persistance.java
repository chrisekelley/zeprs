/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;

/**
 * Filter which manages a ThreadLocal hibernate session.  Obtain the session
 * by calling Persistance.getSession().  Define the JNDI name of the
 * hibernate session factory as an init param to the filter in your web.xml.
 *
 * @author <a href="mailto:jeff@infohazard.org">Jeff Schnitzer</a>
 */
public class Persistance implements Filter {
    /**
     * Filter init param which defines the JNDI name for the hibernate factory
     */
    public static final String HIBERNATE_FACTORY_JNDI_PARAM = "hibernateFactory";

    /**
     * Default value if no init param is set.
     */
    public static final String HIBERNATE_FACTORY_JNDI_DEFAULT = "java:/HibernateSessionFactory";

    /**
     * Holds the current hibernate session, if one has been created.
     */
    protected static ThreadLocal hibernateHolder = new ThreadLocal();

    /**
     */
    protected static SessionFactory factory;

    /**
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialize hibernate
        try {
            new Configuration().configure();
        } catch (HibernateException ex) {
            throw new ServletException(ex);
        }

        // As good a place as any to initialize the factory
        String factoryJndiName = filterConfig.getInitParameter(HIBERNATE_FACTORY_JNDI_PARAM);
        if (factoryJndiName == null)
            factoryJndiName = HIBERNATE_FACTORY_JNDI_DEFAULT;

        try {
            Context ctx = new InitialContext();
            factory = (SessionFactory) ctx.lookup(factoryJndiName);
        } catch (NamingException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (hibernateHolder.get() != null)
            throw new IllegalStateException("A session is already associated with this thread!  "
                    + "Someone must have called getSession() outside of the context "
                    + "of a servlet request.");

        try {
            chain.doFilter(request, response);
        } finally {
            Session sess = (Session) hibernateHolder.get();
            if (sess != null) {
                hibernateHolder.set(null);

                try {
                    sess.close();
                } catch (HibernateException ex) {
                    throw new ServletException(ex);
                }
            }
        }
    }

    /**
     * ONLY ever call this method from within the context of a servlet request
     * (specifically, one that has been associated with this filter).  If you
     * want a Hibernate session at some other time, call getSessionFactory()
     * and open/close the session yourself.
     *
     * @return an appropriate Session object
     */
    public static Session getSession() throws HibernateException {
        Session sess = (Session) hibernateHolder.get();

        if (sess == null) {
            sess = factory.openSession();
            hibernateHolder.set(sess);
        }

        return sess;
    }

    /**
     * @return the hibernate session factory
     */
    public static SessionFactory getSessionFactory() {
        return factory;
    }

    /**
     * This is a simple method to reduce the amount of code that needs
     * to be written every time hibernate is used.
     */
    public static void rollback(Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
            } catch (HibernateException ex) {
                // Probably don't need to do anything - this is likely being
                // called because of another exception, and we don't want to
                // mask it with yet another exception.
            }
        }
    }

    /**
     */
    public void destroy() {
        // Nothing necessary
    }
}
