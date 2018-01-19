package db.DAO.exerciseData;

import common.PersistType;
import common.Result;
import db.POJO.ExerciseData;

import java.util.List;

public interface ExerciseDataDAO {
    Result<List<ExerciseData>> getAll();
    Result<String> insert(ExerciseData exerciseData, PersistType persistType);
}
