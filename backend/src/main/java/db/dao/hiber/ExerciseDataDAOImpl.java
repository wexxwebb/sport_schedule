package db.dao.hiber;

import common.Logged;
import db.dao._inter.ExerciseDataDAO;
import db.entities.Impl.ExerciseDataImpl;
import db.entities.Impl.TrainingImpl;
import db.entities._inter.ExerciseData;
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
public class ExerciseDataDAOImpl implements ExerciseDataDAO {

    @Logged
    private Logger logger;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ExerciseDataImpl> getAll() {
        return doIt(this::_getAll, sessionFactory);
    }

    private List<ExerciseDataImpl> _getAll(Session session) {
        Query query = session.createQuery("FROM ExerciseData");
        return query.getResultList();
    }

    @Override
    public ExerciseDataImpl insert(ExerciseDataImpl exerciseData) {
        return doIt(this::_insert, exerciseData, sessionFactory);
    }

    private ExerciseDataImpl _insert(Session session, ExerciseDataImpl exerciseData) {
        exerciseData.setId((long)session.save(exerciseData));
        return exerciseData;
    }

    @Override
    public List<ExerciseData> searchByName(String term) {
        return null;
    }

    @Override
    public ExerciseData getById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return doIt(this::_delete, id, sessionFactory);
    }

    private boolean _delete(Session session, long id) {
        ExerciseDataImpl exerciseData = session.load(ExerciseDataImpl.class, id);
        session.delete(exerciseData);
        return true;
    }

    @Override
    public ExerciseData update(ExerciseDataImpl exerciseData) {
        return null;
    }
}
