package db.dao.person;

import common.InsertType;
import common.Result;
import db.pojo.Person;
import db.pojo.Sex;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class PersonDAOImpl implements PersonDAO {

    private ConnectionManager connectionManager;

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
    public Result<Person> insert(Person person, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (first_name, last_name, birthday, sex_id) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), ?) RETURNING id"
                    );
                    preparedStatement.setString(1, person.getFirstName());
                    preparedStatement.setString(2, person.getLastName());
                    preparedStatement.setString(3, person.getBirthday());
                    preparedStatement.setInt(4, person.getSexId());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (id, first_name, last_name, birthday, sex_id) " +
                                    "VALUES (?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?)"
                    );
                    preparedStatement.setInt(1, person.getId());
                    preparedStatement.setString(2, person.getFirstName());
                    preparedStatement.setString(3, person.getLastName());
                    preparedStatement.setString(4, person.getBirthday());
                    preparedStatement.setInt(5, person.getSexId());

                }
                preparedStatement.addBatch();

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    person.setId(resultSet.getInt("id"));
                    return new Result<>(person, true, "Success");
                } else {
                    return new Result<>(null, false, "Success");
                }

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
