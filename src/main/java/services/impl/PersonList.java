package services.impl;

import common.Logged;
import common.Result;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.pojo.Person;
import db.connectionManager.ConnectionManager;
import db.connectionManager.ConnectionManagerImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class PersonList {

    @Logged
    private Logger logger;

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
