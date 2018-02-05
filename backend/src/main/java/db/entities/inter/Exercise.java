package db.entities.inter;

import java.io.Serializable;

public interface Exercise extends Serializable {
    Training getTraining();

    void setTraining(Training training);

    long getExerciseId();

    void setExerciseId(long exerciseId);

    ExerciseData getExerciseData();

    void setExerciseData(ExerciseData exercise);

    long getTrainingId();

    void setTrainingId(long trainingId);

    long getId();

    void setId(long id);

    int getApproach();

    void setApproach(int approach);

    int getRepetition();

    void setRepetition(int repetition);

    int getWeigth();

    void setWeigth(int weigth);
}
