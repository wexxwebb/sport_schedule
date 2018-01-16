package pro.kretov.db.DAO.exercise;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.Exercise;

import java.util.List;

public interface ExerciseDAO {
    Result<List<Exercise>> getAll();
    Result<String> persist(Exercise exercise, PersistType persistType);
}
