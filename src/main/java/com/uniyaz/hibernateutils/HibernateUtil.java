package com.uniyaz.hibernateutils;

import com.uniyaz.domain.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Bahadır Memiş
 * @since 5.1
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try{

            Configuration cfg = new Configuration();

            cfg.addAnnotatedClass(Person.class);

            SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

            return sessionFactory;

        } catch (Exception e){

            System.out.println("Session factory oluşturulurken hata oluştu" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
