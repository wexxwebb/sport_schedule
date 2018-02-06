package services.impl;

import com.google.gson.Gson;
import common.Logged;
import common.Result;
import db.dao._inter.ExerciseDAO;
import db.dao._inter.ExerciseDataDAO;
import db.entities.Impl.ExerciseDataImpl;
import db.entities.Impl.TrainingImpl;
import db.entities._inter.Exercise;
import db.entities._inter.ExerciseData;
import db.entities.Impl.ExerciseImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.ExerciseService;

import java.util.List;

import static common.InsertType.NEW;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Logged
    private Logger logger;

    @Autowired
    private ExerciseDAO exerciseDAO;

    @Autowired
    private ExerciseDataDAO exerciseDataDAO;

    private Gson gson = new Gson();

    public ExerciseDAO getExerciseDAO() {
        return exerciseDAO;
    }

    public void setExerciseDAO(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    public ExerciseServiceImpl() {
    }

    @Override
    public Result<String> addExercise(int exerciseId, int trainingId, int approach, int repetition, int weight) {

        ExerciseImpl exercise = new ExerciseImpl(new ExerciseDataImpl(exerciseId),
                new TrainingImpl(trainingId), approach, repetition, weight);
        Result<Exercise> result;
//        if ((result = exerciseDAO.insert(exercise, NEW)).isSuccess()) {
//            ExerciseData exerciseData = exerciseDataDAO.getById(exercise.getExerciseId());
//            if (exerciseDataResult.isSuccess()) {
//                exercise.setExerciseData(exerciseDataResult.get());
//                return new Result<>(gson.toJson(result.get()), true, "Success");
//            }
//        }
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
