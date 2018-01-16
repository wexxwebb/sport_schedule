package pro.kretov.db.DAO.person;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.Person;

import java.util.List;

public interface PersonDAO {
    Result<List<Person>> getAll();
    Result<String> persist(Person person, PersistType persistType);
}
