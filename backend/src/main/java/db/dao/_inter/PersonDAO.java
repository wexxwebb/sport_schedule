package db.dao._inter;

import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.PersonImpl;

import java.io.Serializable;
import java.util.List;

public interface PersonDAO extends Serializable {

    List<PersonImpl> getAll() throws DataIsNotAvailableException;

    PersonImpl insert(PersonImpl person) throws DataIsNotAvailableException;
}
