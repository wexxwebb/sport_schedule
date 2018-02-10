package db.dao.hiber;

import common.Logged;
import db.dao._inter.ExerciseDataDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseDataImpl;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

import static db.dao.DAO.doIt;

@SuppressWarnings("unchecked")
@Component
public class ExerciseDataDAOImpl implements ExerciseDataDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public ExerciseDataDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ExerciseDataImpl> getAll() throws DataIsNotAvailableException {
        try {
            return doIt(this::_getAll, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private List<ExerciseDataImpl> _getAll(Session session) {
        Query query = session.createQuery("FROM ExerciseData");
        return query.getResultList();
    }

    @Override
    public ExerciseDataImpl insert(ExerciseDataImpl exerciseData) throws DataIsNotAvailableException {
        try {
            return doIt(this::_insert, exerciseData, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private ExerciseDataImpl _insert(Session session, ExerciseDataImpl exerciseData) {
        exerciseData.setId((long) session.save(exerciseData));
        return (ExerciseDataImpl) Hibernate.unproxy(exerciseData);
    }

    @Override
    public List<ExerciseDataImpl> searchByName(String term) throws DataIsNotAvailableException {
        try {
            return doIt(this::_searchByName, term, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private List<ExerciseDataImpl> _searchByName(Session session, String term) {
        NativeQuery query = session.createNativeQuery(
                "SELECT * FROM exercise_data WHERE name ILIKE :_ilike", ExerciseDataImpl.class);
        query.setParameter("_ilike", "%" + term + "%");
        return query.list();
    }

    @Override
    public ExerciseDataImpl getById(long id) throws DataIsNotAvailableException {
        try {
            return doIt(this::_getById, id, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private ExerciseDataImpl _getById(Session session, long id) {
        return session.get(ExerciseDataImpl.class, id);
    }

    @Override
    public boolean delete(long id) throws DataIsNotAvailableException {
        try {
            return doIt(this::_delete, id, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private boolean _delete(Session session, long id) {
        ExerciseDataImpl exerciseData = session.get(ExerciseDataImpl.class, id);
        session.delete(exerciseData);
        return true;
    }

}
