package db.xml.xmlWrapper;

import db.entities.Training;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TrainingTable {

    @XmlElement(name = "Training")
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
