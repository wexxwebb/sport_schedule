package db.dao._inter;

import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseDataImpl;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataDAO extends Serializable {

    List<ExerciseDataImpl> getAll() throws DataIsNotAvailableException;

    ExerciseDataImpl insert(ExerciseDataImpl exerciseData) throws DataIsNotAvailableException;

    List<ExerciseDataImpl> searchByName(String term) throws DataIsNotAvailableException;

    ExerciseDataImpl getById(long id) throws DataIsNotAvailableException;

    boolean delete(long id) throws DataIsNotAvailableException;

}
