package consoleTest.persist;

import common.Result;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.pojo.Person;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;

public class TestPerson {

    public static void main(String[] args) {
        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());

//        {
//            Person person = new Person(
//                    "Александр",
//                    "Кретов",
//                    "1989-01-08",
//                    1);
//
//            Result<Person> result;
//            if ((result = personDAO.insert(person, NEW)).isSuccess()) {
//                System.out.println(result.getMessage());
//            } else {
//                System.out.println(result.getMessage());
//            }
//        }

//        {
//            Person person = new Person(
//                    25,
//                    "Натали",
//                    "Портман",
//                    "1990-06-02",
//                    25);
//
//            Result<Person> result;
//            if ((result = personDAO.insert(person, RESTORE)).isSuccess()) {
//                System.out.println(result.getMessage());
//            } else {
//                System.out.println(result.getMessage());
//            }
//        }
//
        {
            Person person = new Person(
                    "Арнольд",
                    "Шварцнеггер",
                    "1970-01-08",
                    1);

            Result<Person> result;
            if ((result = personDAO.insert(person, NEW)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<Person>> result;
        if ((result = personDAO.getAll()).isSuccess()) {
            for (Person person : result.get()) {
                System.out.println(person);
            }
        } else {
            System.out.println(result.getMessage());
        }
    }
}
