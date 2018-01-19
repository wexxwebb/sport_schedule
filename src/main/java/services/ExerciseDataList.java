package services;

import common.Result;
import db.DAO.exercise.ExerciseDAOImpl;
import db.DAO.exerciseData.ExerciseDataDAOImpl;
import db.POJO.ExerciseData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDataList {

    ExerciseDataDAOImpl exerciseDataDAO;

    public ExerciseDataList() {
        exerciseDataDAO = new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
    }

    public List<ExerciseData> getExerciseData(String term) {
        Result<List<ExerciseData>> result = exerciseDataDAO.getSearch(term);
        if (result.isSuccess()) {
            return result.getResult();
        }
        else return new ArrayList<>();
    }
}
