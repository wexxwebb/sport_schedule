package db.dao.hiber;

import db.dao._inter.ExerciseDAO;
import db.entities.Impl.ExerciseImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static db.dao.DAO.doIt;

@Component
public class ExerciseDAOImpl implements ExerciseDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ExerciseImpl> getAll() {
        return null;
    }

    @Override
    public ExerciseImpl insert(ExerciseImpl exercise) {
        return doIt(this::_insert, exercise, sessionFactory);
    }

    private ExerciseImpl _insert(Session session, ExerciseImpl exercise) {
        exercise.setId((long) session.save(exercise));
        return exercise;
    }

    @Override
    public List<ExerciseImpl> getByTrainingId(int trainingId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
