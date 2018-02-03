package services;

import db.entities.ExerciseData;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataService extends Serializable {

    String searchExerciseData(String term);

    String addExerciseData(String name);

    String delExerciseData(int id);

    List<ExerciseData> getExerciseDatalist();
}
