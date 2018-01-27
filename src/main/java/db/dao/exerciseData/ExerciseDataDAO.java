package db.dao.exerciseData;

import common.InsertType;
import common.Result;
import db.pojo.ExerciseData;

import java.util.List;

public interface ExerciseDataDAO {

    Result<List<ExerciseData>> getAll();

    Result<String> insert(ExerciseData exerciseData, InsertType insertType);

    Result<List<ExerciseData>> searchByName(String term);
}
