package db.dao.hiber;

import common.Logged;
import db.dao._interfaces.SexDAO;
import db.entities.Impl.SexImpl;
import db.entities.inter.Sex;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

import static db.dao.DAO.doIt;

@SuppressWarnings("unchecked")
@Repository
public class SexDAOImpl implements SexDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return list of Sex items
     */

    @Override
    public List<Sex> getAll() {
        return doIt(this::_getAll, null, sessionFactory);
    }

    private List<Sex> _getAll(Session session, Object object) {
        Query query = session.createQuery("FROM Sex");
        return query.getResultList();
    }

    /**
     * @param sex POJO, if you want to save new item don't specified id
     * @return POJO with inserted id
     */
    @Override
    public SexImpl insert(SexImpl sex) {
        return doIt(this::_insert, sex, sessionFactory);
    }

    private SexImpl _insert(Session session, SexImpl sex) {
        sex.setId((long) session.save(sex));
        return sex;
    }
}
