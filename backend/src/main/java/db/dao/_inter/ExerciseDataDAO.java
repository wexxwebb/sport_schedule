package db.dao._inter;

import db.entities.Impl.ExerciseDataImpl;
import db.entities._inter.ExerciseData;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataDAO extends Serializable {

    @Nullable
    List<ExerciseDataImpl> getAll();

    @Nullable
    ExerciseDataImpl insert(ExerciseDataImpl exerciseData);

    List<ExerciseDataImpl> searchByName(String term);

    @Nullable
    ExerciseData getById(long id);

    boolean delete(long id);

    @Nullable
    ExerciseData update(ExerciseDataImpl exerciseData);
}
