package com.uniyaz.dao;

import com.uniyaz.domain.Person;
import com.uniyaz.hibernateutils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author Samet BUDAK
 * @since
 */
public class PersonDao {

    private SessionFactory sessionFactory;

    public PersonDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Person findPersonById(Integer id) {
        String hql = "Select person from Person person where id = :id";
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        query.setParameter("id",1);

        Person person = (Person) query.uniqueResult();

        return person;
    }
}
