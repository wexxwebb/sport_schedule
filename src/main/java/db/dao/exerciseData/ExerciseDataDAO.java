package db.dao.exerciseData;

import common.InsertType;
import common.Result;
import db.pojo.ExerciseData;

import java.util.List;

public interface ExerciseDataDAO {

    Result<List<ExerciseData>> getAll();

    Result<ExerciseData> insert(ExerciseData exerciseData, InsertType insertType);

    Result<List<ExerciseData>> searchByName(String term);

    Result<ExerciseData> getById(int id);

    Result<String> delete(int id);

    Result<ExerciseData> update(ExerciseData exerciseData);
}
