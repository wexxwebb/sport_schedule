package db.DAO.exercise;

import common.PersistType;
import common.Result;
import db.POJO.Exercise;

import java.util.List;

public interface ExerciseDAO {
    Result<List<Exercise>> getAll();
    Result<String> persist(Exercise exercise, PersistType persistType);
}
