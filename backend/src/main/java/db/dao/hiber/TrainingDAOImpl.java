package db.dao.hiber;

import common.Logged;
import db.dao._inter.TrainingDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.TrainingImpl;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

import static db.dao.DAO.doIt;

@SuppressWarnings("unchecked")
@Component
public class TrainingDAOImpl implements TrainingDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public TrainingDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param userId user's ID
     * @return List of items TrainingImpl
     */
    @Override
    public List<TrainingImpl> getAll(long userId) throws DataIsNotAvailableException {
        try {
            return doIt(this::_getAll, userId, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private List<TrainingImpl> _getAll(Session session, long userId) {
        Query query = session.createQuery("from Training where user_id = :user_id");
        query.setParameter("user_id", userId);
        return query.getResultList();
    }

    @Override
    public TrainingImpl insert(TrainingImpl training) throws DataIsNotAvailableException {
        try {
            return doIt(this::_insert, training, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private TrainingImpl _insert(Session session, TrainingImpl training) {
        training.setId((long) session.save(training));
        return training;
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
        TrainingImpl trainingImpl = session.get(TrainingImpl.class, id);
        session.delete(trainingImpl);
        return true;
    }

    @Override
    public TrainingImpl getById(long id) throws DataIsNotAvailableException {
        try {
            return doIt(this::_getById, id, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private TrainingImpl _getById(Session session, long id) {
        TrainingImpl training = session.load(TrainingImpl.class, id);
        Hibernate.initialize(training.getExerciseCollection());
        return training;
    }
}
