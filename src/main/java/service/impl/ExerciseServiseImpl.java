package service.impl;

import com.google.gson.Gson;
import common.Result;
import db.dao.exercise.ExerciseDAO;
import db.dao.exerciseData.ExerciseDataDAO;
import db.pojo.Exercise;
import db.pojo.ExerciseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExerciseServise;

import java.util.List;

import static common.InsertType.NEW;

@Service
public class ExerciseServiseImpl implements ExerciseServise {

    private ExerciseDAO exerciseDAO;

    private ExerciseDataDAO exerciseDataDAO;

    public ExerciseDataDAO getExerciseDataDAO() {
        return exerciseDataDAO;
    }

    @Autowired
    public void setExerciseDataDAO(ExerciseDataDAO exerciseDataDAO) {
        this.exerciseDataDAO = exerciseDataDAO;
    }

    public Gson getGson() {
        return gson;
    }

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    private Gson gson = new Gson();

    public ExerciseDAO getExerciseDAO() {
        return exerciseDAO;
    }

    public void setExerciseDAO(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    public ExerciseServiseImpl() {
    }


    @Override
    public Result<String> addExercise(int exerciseId, int trainingId, int approach, int repetition, int weight) {

        Exercise exercise = new Exercise(exerciseId, trainingId, approach, repetition, weight);
        Result<Exercise> result;
        if ((result = exerciseDAO.insert(exercise, NEW)).isSuccess()) {
            Result<ExerciseData> exerciseDataResult = exerciseDataDAO.getById(exercise.getExerciseId());
            if (exerciseDataResult.isSuccess()) {
                exercise.setExerciseData(exerciseDataResult.get());
                return new Result<>(gson.toJson(result.get()), true, "Success");
            }
        }
        return new Result<>("0", false, "Can't insert");
    }

    @Override
    public Result<String> delExercise(int id) {
        return exerciseDAO.delete(id);
    }

    @Override
    public Result<List<Exercise>> getByTrainindId(int trainingId) {

        return exerciseDAO.getByTrainingId(trainingId);

    }
}
