package db.entities.Impl;

import db.entities._inter.ExerciseData;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.GenerationType.SEQUENCE;

@XmlRootElement
@Entity(name = "ExerciseData")
@Table(name = "exercise_data")
public class ExerciseDataImpl implements ExerciseData {

    private long id;
    private String name;

    public ExerciseDataImpl() {
    }

    public ExerciseDataImpl(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public ExerciseDataImpl(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "training_data_seq", allocationSize = 1)
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExerciseData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
