package db.xml.xmlWrapper;

import db.POJO.Exercise;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ExerciseTable {

    private List<Exercise> exerciseList;

    public ExerciseTable() {

    }

    public ExerciseTable(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
