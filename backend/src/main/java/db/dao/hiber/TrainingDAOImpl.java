package db.dao.hiber;

import common.Logged;
import common.TimePeriod;
import db.dao._interfaces.TrainingDAO;
import db.entities.inter.Training;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.Nullable;
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
    @Nullable
    public List<Training> getAll(long userId, TimePeriod timePeriod) {
//        switch (timePeriod) {
//            case PAST: return doIt(this::_getPAST, userId, sessionFactory, logger);
//
//        }
        return doIt(this::_getAll, userId, sessionFactory);
    }

    private List<Training> _getAll(Session session, long userId) {
        Query query = session.createQuery("FROM Training WHERE id = " + userId);
        return query.getResultList();
    }

    private List<Training> _getPAST(Session session, long userId) {
        Query query = session.createQuery("FROM Training WHERE id = " + userId);
        return query.getResultList();
    }

    @Override
    @Nullable
    public Training insert(Training training) {
        return doIt(this::_insert, training, sessionFactory);
    }

    private Training _insert(Session session, Training training) {
        training.setId((long) session.save(training));
        return training;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Training getById(int id) {
        return null;
    }
}
