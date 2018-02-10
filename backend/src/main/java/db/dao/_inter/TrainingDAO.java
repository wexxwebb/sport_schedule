package db.dao._inter;

import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.TrainingImpl;

import java.io.Serializable;
import java.util.List;

public interface TrainingDAO extends Serializable {

    List<TrainingImpl> getAll(long userId) throws DataIsNotAvailableException;

    TrainingImpl insert(TrainingImpl training) throws DataIsNotAvailableException;

    boolean delete(long id) throws DataIsNotAvailableException;

    TrainingImpl getById(long id) throws DataIsNotAvailableException;
}
