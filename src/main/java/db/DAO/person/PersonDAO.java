package db.DAO.person;

import common.PersistType;
import common.Result;
import db.POJO.Person;

import java.util.List;

public interface PersonDAO {
    Result<List<Person>> getAll();
    Result<String> insert(Person person, PersistType persistType);
}
