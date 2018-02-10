package db.dao.hiber;

import common.Logged;
import db.dao._inter.UserDataDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.UserDataImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static db.dao.DAO.doIt;

@Component
public class UserDataDAOImpl implements UserDataDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public UserDataDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDataImpl insert(db.entities.Impl.UserDataImpl userData) throws DataIsNotAvailableException {
        try {
            return doIt(this::_insert, userData, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private UserDataImpl _insert(Session session, db.entities.Impl.UserDataImpl userData) {
        userData.setId((long) session.save(userData));
        return userData;
    }

    @Override
    public UserDataImpl getById(long id) throws DataIsNotAvailableException {
        try {
            return doIt(this::_getById, id, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private UserDataImpl _getById(Session session, long id) {
        return session.get(UserDataImpl.class, id);
    }

    @Override
    public UserDataImpl getByLogin(String login) throws DataIsNotAvailableException {
        try {
            return doIt(this::_getByLogin, login, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private UserDataImpl _getByLogin(Session session, String login) {
        Query query = session.createQuery("from UserData where login=:login");
        query.setParameter("login", login);
        return (UserDataImpl) query.uniqueResult();
    }
}



















