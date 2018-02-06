package db.dao.hiber;

import common.InsertType;
import common.Result;
import db.dao._inter.ExerciseDAO;
import db.entities._inter.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseDAOImpl implements ExerciseDAO {
    @Override
    public Result<List<Exercise>> getAll() {
        return null;
    }

    @Override
    public Result<Exercise> insert(Exercise exercise, InsertType insertType) {
        return null;
    }

    @Override
    public Result<List<Exercise>> getByTrainingId(int trainingId) {
        return null;
    }

    @Override
    public Result<String> delete(int id) {
        return null;
    }
}
