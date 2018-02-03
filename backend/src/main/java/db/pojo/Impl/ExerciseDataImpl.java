package db.pojo.Impl;

import db.pojo.ExerciseData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
