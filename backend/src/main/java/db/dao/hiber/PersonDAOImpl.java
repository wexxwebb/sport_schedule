package db.dao.hiber;

import common.InsertType;
import common.Logged;
import common.Result;
import db.dao.DAO;
import db.dao._interfaces.PersonDAO;
import db.entities.Impl.PersonImpl;
import db.entities.inter.Person;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAOImpl implements PersonDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Result<List<Person>> getAll() {
        return null;
    }

    @Override
    public PersonImpl insert(PersonImpl person, InsertType insertType) {
        return DAO.doIt(this::_insert, person, sessionFactory);
    }

    private PersonImpl _insert(Session session, PersonImpl person) {
        person.setId((long)session.save(person));
        return person;
    }
}
