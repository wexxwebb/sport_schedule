package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StateImpl implements State {

    private int id;
    private String state;
    private boolean enabled;
    private String role;

    public StateImpl() {
    }

    public StateImpl(String state) {
        this.state = state;
    }

    public StateImpl(int id, String state, boolean enabled, String role) {
        this.id = id;
        this.state = state;
        this.enabled = enabled;
        this.role = role;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
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
    public String getState() {
        return state;
    }

    @Override
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
