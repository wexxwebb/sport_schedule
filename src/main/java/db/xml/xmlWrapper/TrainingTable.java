package db.xml.xmlWrapper;

import db.POJO.Training;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TrainingTable {

    private List<Training> trainingList;

    public TrainingTable() {

    }

    public TrainingTable(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
