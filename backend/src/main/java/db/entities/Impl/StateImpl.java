package db.entities.Impl;

import db.entities.State;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StateImpl implements State {

    private long id;
    private String state;
    private boolean enabled;
    private String role;

    public StateImpl() {
    }

    public StateImpl(String state) {
        this.state = state;
    }

    public StateImpl(long id, String state, boolean enabled, String role) {
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
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
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
