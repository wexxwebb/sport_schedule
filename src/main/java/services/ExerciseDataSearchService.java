package services;

import db.POJO.ExerciseData;

import java.util.List;

public interface ExerciseDataSearchService {

    List<ExerciseData> getExerciseData(String term);
}
