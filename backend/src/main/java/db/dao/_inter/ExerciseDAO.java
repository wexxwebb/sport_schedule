package db.dao._inter;

import db.entities.Impl.ExerciseImpl;
import db.entities._inter.Exercise;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDAO extends Serializable {

    List<ExerciseImpl> getAll();

    ExerciseImpl insert(ExerciseImpl exercise);

    List<ExerciseImpl> getByTrainingId(int trainingId);

    boolean delete(int id);
}
