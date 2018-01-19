package consoleTest.persist;

import common.Result;
import common.SDF;
import db.DAO.person.PersonDAO;
import db.DAO.person.PersonDAOImpl;
import db.POJO.Person;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestPerson {

    public static void main(String[] args) {
        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());

        {
            Person person = new Person(
                    "Александр",
                    "Кретов",
                    "1989-01-08",
                    1);

            Result<String> result;
            if ((result = personDAO.persist(person, NEW)).isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            Person person = new Person(
                    25,
                    "Натали",
                    "Портман",
                    "1990-06-02",
                    25);

            Result<String> result;
            if ((result = personDAO.persist(person, RESTORE)).isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            Person person = new Person(
                    "Арнольд",
                    "Шварцнеггер",
                    "1970-01-08",
                    1);

            Result<String> result;
            if ((result = personDAO.persist(person, NEW)).isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<Person>> result;
        if ((result = personDAO.getAll()).isSuccess()) {
            for (Person person : result.getResult()) {
                System.out.println(person);
            }
        } else {
            System.out.println(result.getMessage());
        }
    }
}
