package com.uniyaz.service;

import com.uniyaz.dao.PersonDao;
import com.uniyaz.domain.Person;

/**
 * @author Samet BUDAK
 * @since
 */
public class PersonService {

    PersonDao personDao;

    public PersonService() {
        personDao = new PersonDao();
    }

    public Person findById(Integer id){
        return personDao.findPersonById(id);
    }
}
