package services;

import common.Result;
import db.entities.Exercise;

import java.io.Serializable;
import java.util.List;

public interface ExerciseService extends Serializable {

    Result<String> addExercise(int trainingId, int exerciseId, int approach,
                               int repetition, int weight);

    Result<String> delExercise(int id);

    Result<List<Exercise>> getByTrainindId(int trainingId);
}
