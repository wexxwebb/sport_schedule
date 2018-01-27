package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExerciseData {

    private int id;
    private String name;

    public ExerciseData() {
    }

    public ExerciseData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExerciseData(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

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
