package services.impl;

import common.Result;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.pojo.Person;
import db.connectionManager.ConnectionManager;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

public class PersonList {

    private ConnectionManager connectionManager;

    public PersonList() {
        this.connectionManager = ConnectionManagerImpl.getInstance();
    }

    public List<Person> getPersonList() {
        PersonDAO personDAO =
                new PersonDAOImpl(connectionManager);
        Result<List<Person>> result;
        if ((result = personDAO.getAll()).isSuccess()) {
            return result.get();
        } else {
            return null;
        }
    }
}
