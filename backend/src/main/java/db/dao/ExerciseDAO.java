package db.dao;

import common.InsertType;
import common.Result;
import db.entities.Exercise;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDAO extends Serializable {

    Result<List<Exercise>> getAll();

    Result<Exercise> insert(Exercise exercise, InsertType insertType);

    Result<List<Exercise>> getByTrainingId(int trainingId);

    Result<String> delete(int id);
}
