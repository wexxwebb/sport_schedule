package db.dao.dto.Impl;

import db.entities.Impl.ExerciseImpl;

public class ExerciseDTOImpl implements db.dao.dto._inter.ExerciseDTO {

    private long id;
    private String exerciseDataName;
    private int approach;
    private int repetition;
    private int weight;

    public ExerciseDTOImpl(ExerciseImpl exercise) {
        this.id = exercise.getId();
        this.exerciseDataName = exercise.getExerciseData().getName();
        this.approach = exercise.getApproach();
        this.repetition = exercise.getRepetition();
        this.weight = exercise.getWeight();
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setExerciseDataName(String exerciseDataName) {
        this.exerciseDataName = exerciseDataName;
    }

    @Override
    public void setApproach(int approach) {
        this.approach = approach;
    }

    @Override
    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getExerciseDataName() {
        return exerciseDataName;
    }

    @Override
    public int getApproach() {
        return approach;
    }

    @Override
    public int getRepetition() {
        return repetition;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
