package db.dao.dto._inter;

public interface ExerciseDTO {

    void setId(long id);

    void setExerciseDataName(String exerciseDataName);

    void setApproach(int approach);

    void setRepetition(int repetition);

    void setWeight(int weight);

    long getId();

    String getExerciseDataName();

    int getApproach();

    int getRepetition();

    int getWeight();
}
