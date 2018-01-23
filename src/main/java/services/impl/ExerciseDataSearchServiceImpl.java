package services.impl;

import common.Result;
import db.dao.exerciseData.ExerciseDataDAOImpl;
import db.pojo.ExerciseData;
import db.connectionManager.ConnectionManagerImpl;
import services.ExerciseDataSearchService;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDataSearchServiceImpl implements ExerciseDataSearchService {

    private ExerciseDataDAOImpl exerciseDataDAO;

    public ExerciseDataSearchServiceImpl() {
        exerciseDataDAO = new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
    }

    public List<ExerciseData> getExerciseData(String term) {
        Result<List<ExerciseData>> result = exerciseDataDAO.getSearch(term);
        if (result.isSuccess()) {
            return result.get();
        } else
            return new ArrayList<>();
    }
}
