package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class State {

    private int id;
    private String state;

    public State() {
    }

    public State(int id, String name) {
        this.id = id;
        this.state = name;
    }

    public State(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
