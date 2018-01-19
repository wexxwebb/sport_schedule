package db.DAO.person;

import common.PersistType;
import common.Result;
import db.POJO.Person;
import db.POJO.Sex;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class PersonDAOImpl implements PersonDAO {

    ConnectionManager connectionManager;

    public PersonDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<Person>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT p.id, " +
                                "first_name, " +
                                "last_name, " +
                                "birthday, " +
                                "sex_id, " +
                                "sex " +
                                "FROM person p INNER JOIN sex ON p.sex_id = sex.id"
                );

                List<Person> personList = new ArrayList<>();
                while (resultSet.next()) {
                    Sex sex = new Sex(
                            resultSet.getInt("sex_id"),
                            resultSet.getString("sex")
                    );
                    Person person = new Person(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getObject("birthday").toString(),
                            sex
                    );
                    personList.add(person);
                }
                return new Result<>(personList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> insert(Person person, PersistType persistType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = null;
                if (persistType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (first_name, last_name, birthday, sex_id) " +
                                 "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), ?)"
                    );
                    preparedStatement.setString(1, person.getFirstName());
                    preparedStatement.setString(2, person.getLastName());
                    preparedStatement.setString(3, person.getBirthday().toString());
                    preparedStatement.setInt(4, person.getSexId());

                } else if (persistType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (id, first_name, last_name, birthday, sex_id) " +
                                    "VALUES (?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?)"
                    );
                    preparedStatement.setInt(1, person.getId());
                    preparedStatement.setString(2, person.getFirstName());
                    preparedStatement.setString(3, person.getLastName());
                    preparedStatement.setString(4, person.getBirthday().toString());
                    preparedStatement.setInt(5, person.getSexId());

                }
                preparedStatement.addBatch();
                int[] counts = preparedStatement.executeBatch();

                return new Result<>(
                        String.format("Inserted %d lines", counts[0]),
                        true,
                        "Success"
                );

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
