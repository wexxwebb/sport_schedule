package db.xml.xmlWrapper;

import db.POJO.ExerciseData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ExerciseDataTable {

    private List<ExerciseData> exerciseDataList;

    public ExerciseDataTable() {

    }

    public ExerciseDataTable(List<ExerciseData> exerciseDataList) {
        this.exerciseDataList = exerciseDataList;
    }

    public List<ExerciseData> getExerciseDataList() {
        return exerciseDataList;
    }

    public void setExerciseDataList(List<ExerciseData> exerciseDataList) {
        this.exerciseDataList = exerciseDataList;
    }
}