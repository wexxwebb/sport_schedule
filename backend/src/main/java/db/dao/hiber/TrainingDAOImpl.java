package db.dao.hiber;

import common.Logged;
import common.TimePeriod;
import db.dao._inter.TrainingDAO;
import db.entities.Impl.TrainingImpl;
import db.entities._inter.Training;
import org.apache.log4j.Logger;
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
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param userId     user's ID
     * @param timePeriod time period can be ALL, PAST, TODAY, FUTURE
     * @return List of items Training
     */
    @Override
    public List<Training> getAll(long userId, TimePeriod timePeriod) {
        return doIt(this::_getAll, userId, sessionFactory);
    }

    private List<Training> _getAll(Session session, long userId) {
        Query query = session.createQuery("from Training where user_id = :user_id");
        query.setParameter("user_id", userId);
        return query.getResultList();
    }

    @Override
    public TrainingImpl insert(TrainingImpl training) {
        return doIt(this::_insert, training, sessionFactory);
    }

    private TrainingImpl _insert(Session session, TrainingImpl training) {
        training.setId((long) session.save(training));
        return training;
    }

    @Override
    public boolean delete(long id) {
        return doIt(this::_delete, id, sessionFactory);
    }

    private boolean _delete(Session session, long id) {
        TrainingImpl trainingImpl = session.get(TrainingImpl.class, id);
        session.delete(trainingImpl);
        return true;
    }

    @Override
    public Training getById(long id) {
        return null;
    }
}
