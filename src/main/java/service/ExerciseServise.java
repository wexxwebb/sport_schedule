package service;

import common.Result;
import db.pojo.Exercise;

import java.util.List;

public interface ExerciseServise {

    Result<String> addExercise(int trainingId, int exerciseId, int approach,
                               int repetition, int weight);

    Result<String> delExercise(int id);

    Result<List<Exercise>> getByTrainindId(int trainingId);
}
