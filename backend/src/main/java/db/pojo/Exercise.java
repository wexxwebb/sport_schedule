package db.pojo;

public interface Exercise {
    Training getTraining();

    void setTraining(Training training);

    int getExerciseId();

    void setExerciseId(int exerciseId);

    ExerciseData getExerciseData();

    void setExerciseData(ExerciseData exercise);

    int getTrainingId();

    void setTrainingId(int trainingId);

    int getId();

    void setId(int id);

    int getApproach();

    void setApproach(int approach);

    int getRepetition();

    void setRepetition(int repetition);

    int getWeigth();

    void setWeigth(int weigth);
}
