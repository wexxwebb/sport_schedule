package db.dao._inter;

import common.InsertType;
import common.Result;
import db.entities.Impl.PersonImpl;
import db.entities._inter.Person;

import java.io.Serializable;
import java.util.List;

public interface PersonDAO extends Serializable {

    Result<List<Person>> getAll();

    PersonImpl insert(PersonImpl person, InsertType insertType);
}
