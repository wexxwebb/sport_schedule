package services.impl;

import com.google.gson.Gson;
import common.Result;
import db.dao.exercise.ExerciseDAO;
import db.pojo.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ExerciseServise;

import java.util.List;

import static common.InsertType.NEW;

@Service
public class ExerciseServiseImpl implements ExerciseServise {

    @Autowired
    private ExerciseDAO exerciseDAO;

    private Gson gson = new Gson();

    public ExerciseDAO getExerciseDAO() {
        return exerciseDAO;
    }

    public void setExerciseDAO(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public Result<String> addExercise(int trainingId, int exerciseId, int approach, int repetition, int weight) {

        Exercise exercise = new Exercise(exerciseId, trainingId, approach, repetition, weight);
        Result<Exercise> result;
        if ((result = exerciseDAO.insert(exercise, NEW)).isSuccess()) {
            return new Result<>(gson.toJson(result.get()), true, "Success");
        }
        return new Result<>("0", false, "Can't insert");
    }

    @Override
    public Result<String> delExercise(int id) {
        return null;
    }

    @Override
    public Result<List<Exercise>> getByTrainindId(int trainingId) {

        return exerciseDAO.getByTrainingId(trainingId);

    }
}
