package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExerciseDataImpl implements ExerciseData {

    private int id;
    private String name;

    public ExerciseDataImpl() {
    }

    public ExerciseDataImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExerciseDataImpl(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
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
