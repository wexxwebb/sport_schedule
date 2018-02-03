package db.dao;

import common.InsertType;
import common.Result;
import db.pojo.ExerciseData;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataDAO extends Serializable {

    Result<List<ExerciseData>> getAll();

    Result<ExerciseData> insert(ExerciseData exerciseData, InsertType insertType);

    Result<List<ExerciseData>> searchByName(String term);

    Result<ExerciseData> getById(long id);

    Result<String> delete(int id);

    Result<ExerciseData> update(ExerciseData exerciseData);
}
