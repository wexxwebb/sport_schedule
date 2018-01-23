package db.xml.xmlWrapper;

import db.pojo.State;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StateTable {

    @XmlElement(name = "State")
    private List<State> stateList;

    public StateTable() {
    }

    public StateTable(List<State> stateList) {
        this.stateList = stateList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }
}
