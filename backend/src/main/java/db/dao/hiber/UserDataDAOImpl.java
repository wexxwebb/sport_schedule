package db.dao.hiber;

import common.InsertType;
import common.Logged;
import db.dao._inter.UserDataDAO;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.UserData;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static db.dao.DAO.doIt;

@Component
public class UserDataDAOImpl implements UserDataDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserData> getAll() {
        return null;
    }

    @Override
    public UserDataImpl insert(db.entities.Impl.UserDataImpl userData, InsertType insertType) {
        return doIt(this::_insert, userData, sessionFactory);
    }

    private UserDataImpl _insert(Session session, db.entities.Impl.UserDataImpl userData) {
        userData.setId((long)session.save(userData));
        return userData;
    }

    @Override
    public UserDataImpl getById(long id) {
        return doIt(this::_getById, id, sessionFactory);
    }

    private UserDataImpl _getById(Session session, long id) {
        return session.get(UserDataImpl.class, id);
    }

    @Override
    public UserDataImpl getByLogin(String login) {
        return doIt(this::_getByLogin, login, sessionFactory);
    }

    private UserDataImpl _getByLogin(Session session, String login) {
        Query query = session.createQuery("from UserData where login=:login");
        query.setParameter("login", login);
        return (UserDataImpl) query.uniqueResult();
    }
}



















