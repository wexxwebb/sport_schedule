package services;

import db.pojo.ExerciseData;

import java.util.List;

public interface ExerciseDataSearchService {

    List<ExerciseData> getExerciseData(String term);
}
