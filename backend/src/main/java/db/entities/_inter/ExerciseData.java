package db.entities._inter;

import java.io.Serializable;

public interface ExerciseData extends Serializable {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    @Override
    String toString();
}
