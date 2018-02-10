package db.dao._inter;

import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseImpl;

import java.io.Serializable;

public interface ExerciseDAO extends Serializable {

    ExerciseImpl insert(ExerciseImpl exercise) throws DataIsNotAvailableException;

    boolean delete(long id) throws DataIsNotAvailableException;
}
