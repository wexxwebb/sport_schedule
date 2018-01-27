package db.dao.person;

import common.InsertType;
import common.Result;
import db.pojo.Person;

import java.util.List;

public interface PersonDAO {
    Result<List<Person>> getAll();
    Result<Person> insert(Person person, InsertType insertType);
}
