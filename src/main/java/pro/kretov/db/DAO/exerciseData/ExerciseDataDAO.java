package pro.kretov.db.DAO.exerciseData;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.ExerciseData;

import java.util.List;

public interface ExerciseDataDAO {
    Result<List<ExerciseData>> getAll();
    Result<String> persist(ExerciseData exerciseData, PersistType persistType);
}
