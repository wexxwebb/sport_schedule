package service.impl;

import common.Result;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.pojo.Person;

import java.util.List;

public class PersonList {

    public PersonList() {

    }

    public List<Person> getPersonList() {
        PersonDAO personDAO =
                new PersonDAOImpl();
        Result<List<Person>> result;
        if ((result = personDAO.getAll()).isSuccess()) {
            return result.get();
        } else {
            return null;
        }
    }
}
