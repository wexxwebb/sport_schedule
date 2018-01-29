package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class State {

    private int id;
    private String state;
    private boolean enabled;
    private String role;

    public State() {
    }

    public State(String state) {
        this.state = state;
    }

    public State(int id, String state, boolean enabled, String role) {
        this.id = id;
        this.state = state;
        this.enabled = enabled;
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }
}
