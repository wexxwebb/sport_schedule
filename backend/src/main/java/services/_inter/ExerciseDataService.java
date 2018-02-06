package services._inter;

import db.entities._inter.ExerciseData;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataService extends Serializable {

    String searchExerciseData(String term);

    String addExerciseData(String name);

    String delExerciseData(long id);

    List<ExerciseData> getExerciseDataList();
}
