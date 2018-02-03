package db.dao;

import common.InsertType;
import common.Result;
import db.entities.Person;

import java.io.Serializable;
import java.util.List;

public interface PersonDAO extends Serializable {
    Result<List<Person>> getAll();
    Result<Person> insert(Person person, InsertType insertType);
}
