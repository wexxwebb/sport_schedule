package db.entities.Impl;

import db.entities._inter.Exercise;
import db.entities._inter.ExerciseData;
import db.entities._inter.Training;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class ExerciseImpl implements Exercise {

    private long id;
    private long exerciseId;
    private ExerciseData exercise;
    private long trainingId;
    private Training training;
    private int approach;
    private int repetition;
    private int weigth;

    public ExerciseImpl() {
    }

    public ExerciseImpl(long id, ExerciseData exercise, int trainingId, int approach, int repetition, int weigth) {
        this.id = id;
        this.exercise = exercise;
        this.trainingId = trainingId;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    public ExerciseImpl(long id, long exerciseId, int trainingId, int approach, int repetition, int weigth) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    public ExerciseImpl(long exerciseId, int trainingId, int approach, int repetition, int weigth) {
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }


    public ExerciseImpl(long id, ExerciseData exercise, Training training, int approach, int repetition, int weigth) {
        this.id = id;
        this.exercise = exercise;
        this.training = training;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    @Override
    public Training getTraining() {
        return training;
    }

    @Override
    public void setTraining(Training training) {
        this.training = training;
    }


    @Override
    public long getExerciseId() {
        return exerciseId;
    }

    @Override
    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public ExerciseData getExerciseData() {
        return exercise;
    }

    @Override
    public void setExerciseData(ExerciseData exercise) {
        this.exercise = exercise;
    }

    @Override
    public long getTrainingId() {
        return trainingId;
    }

    @Override
    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int getApproach() {
        return approach;
    }

    @Override
    public void setApproach(int approach) {
        this.approach = approach;
    }


    @Override
    public int getRepetition() {
        return repetition;
    }

    @Override
    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }


    @Override
    public int getWeigth() {
        return weigth;
    }

    @Override
    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseId=" + exerciseId +
                ", trainingId=" + trainingId +
                ", approach=" + approach +
                ", repetition=" + repetition +
                ", weigth=" + weigth +
                '}';
    }
}
