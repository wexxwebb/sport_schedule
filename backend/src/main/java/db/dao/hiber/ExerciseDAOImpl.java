package db.dao.hiber;

import common.Logged;
import db.dao._inter.ExerciseDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static db.dao.DAO.doIt;

@Component
public class ExerciseDAOImpl implements ExerciseDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public ExerciseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ExerciseImpl insert(ExerciseImpl exercise) throws DataIsNotAvailableException {
        try {
            return doIt(this::_insert, exercise, sessionFactory);
        } catch (Exception e) {
            logger.error(e);
            throw new DataIsNotAvailableException(e);
        }
    }

    private ExerciseImpl _insert(Session session, ExerciseImpl exercise) {
        exercise.setId((long) session.save(exercise));
        return exercise;
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
        ExerciseImpl exercise = session.get(ExerciseImpl.class, id);
        session.delete(exercise);
        return true;
    }
}
