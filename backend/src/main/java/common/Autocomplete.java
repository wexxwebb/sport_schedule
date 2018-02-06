package common;

import java.io.Serializable;

public class Autocomplete implements Serializable {
    private String label;
    private long id;

    public Autocomplete(String label, long id) {
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
