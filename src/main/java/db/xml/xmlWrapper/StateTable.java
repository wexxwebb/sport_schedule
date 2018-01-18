package db.xml.xmlWrapper;

import db.POJO.State;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class StateTable {

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
