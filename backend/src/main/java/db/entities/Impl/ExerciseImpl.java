package db.entities.Impl;

import db.entities._inter.Exercise;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.GenerationType.SEQUENCE;

@XmlRootElement
@Entity(name = "Exercise")
@Table(name = "exercise")
public class ExerciseImpl implements Exercise {

    private long id;
    private ExerciseDataImpl exercise;
    private TrainingImpl training;
    private int approach;
    private int repetition;
    private int weigth;

    public ExerciseImpl() {
    }

    public ExerciseImpl(long id) {
        this.id = id;
    }

    public ExerciseImpl(ExerciseDataImpl exercise, TrainingImpl training,
                        int approach, int repetition, int weight) {
        this.exercise = exercise;
        this.training = training;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weight;
    }

    public ExerciseImpl(long id, ExerciseDataImpl exercise, TrainingImpl training,
                        int approach, int repetition, int weigth) {
        this.id = id;
        this.exercise = exercise;
        this.training = training;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @Override
    public TrainingImpl getTraining() {
        return training;
    }

    @Override
    public void setTraining(TrainingImpl training) {
        this.training = training;
    }

    @Override
    public ExerciseDataImpl getExerciseData() {
        return exercise;
    }

    @Override
    public void setExerciseData(ExerciseDataImpl exercise) {
        this.exercise = exercise;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "exercise_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "hibernateSeq")
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
                ", approach=" + approach +
                ", repetition=" + repetition +
                ", weigth=" + weigth +
                '}';
    }
}
