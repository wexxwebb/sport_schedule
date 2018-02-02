package db.pojo;

import java.io.Serializable;

public interface ExerciseData extends Serializable {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    @Override
    String toString();
}
