package pro.kretov._consoleTest.persist;

import pro.kretov.common.Result;
import pro.kretov.common.SDF;
import pro.kretov.db.DAO.person.PersonDAO;
import pro.kretov.db.DAO.person.PersonDAOImpl;
import pro.kretov.db.POJO.Person;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.sql.Date;
import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

public class TestPerson {

    public static void main(String[] args) {
        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());

        {
            Person person = new Person(
                    "Александр",
                    "Кретов",
                    new Date(SDF.getDate("1989-01-08").getResult().getTime()),
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
                    new Date(SDF.getDate("1990-06-02").getResult().getTime()),
                    25);

            Result<String> result;
            if ((result = personDAO.persist(person, RESTORE)).isSuccess()) {
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
