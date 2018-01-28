package services;

import common.Result;
import db.pojo.ExerciseData;

import java.util.List;

public interface ExerciseDataService {

    String searchExerciseData(String term);

    String addExerciseData(String name);

    String delExerciseData(int id);

    List<ExerciseData> getExerciseDatalist();
}
