package db.dao.hiber;

import common.Logged;
import db.dao.DAO;
import db.dao._inter.PersonDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.PersonImpl;
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
    public PersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PersonImpl> getAll() throws DataIsNotAvailableException {
        return null;
    }

    @Override
    public PersonImpl insert(PersonImpl person) throws DataIsNotAvailableException {
        try {
            return DAO.doIt(this::_insert, person, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private PersonImpl _insert(Session session, PersonImpl person) {
        person.setId((long) session.save(person));
        return person;
    }
}
