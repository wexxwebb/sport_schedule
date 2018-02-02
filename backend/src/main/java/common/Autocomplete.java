package common;

import java.io.Serializable;

public class Autocomplete implements Serializable {
    private String label;
    private String id;

    public Autocomplete(String label, String id) {
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
