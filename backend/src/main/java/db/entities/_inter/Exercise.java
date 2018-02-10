package db.entities._inter;

import db.entities.Impl.ExerciseDataImpl;
import db.entities.Impl.TrainingImpl;

import java.io.Serializable;

public interface Exercise extends Serializable {

    Training getTraining();

    void setTraining(TrainingImpl training);

    ExerciseData getExerciseData();

    void setExerciseData(ExerciseDataImpl exercise);

    long getId();

    void setId(long id);

    int getApproach();

    void setApproach(int approach);

    int getRepetition();

    void setRepetition(int repetition);

    int getWeight();

    void setWeight(int weight);

}
