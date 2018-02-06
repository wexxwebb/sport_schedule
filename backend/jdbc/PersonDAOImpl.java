package db.dao.jdbc;

import common.InsertType;
import common.Logged;
import common.Result;
import db.dao._inter.PersonDAO;
import db.entities._inter.Person;
import db.entities.Impl.PersonImpl;
import db.connectionManager.ConnectionManager;
import db.entities.Impl.SexImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class PersonDAOImpl implements PersonDAO {

    @Logged
    private Logger logger;

    private ConnectionManager connectionManager;

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public PersonDAOImpl() {

    }

    @Override
    public Result<List<Person>> getAll() {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
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
                    SexImpl sex = new SexImpl(
                            resultSet.getInt("sex_id"),
                            resultSet.getString("sex")
                    );
                    Person person = new PersonImpl(
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public PersonImpl insert(PersonImpl person, InsertType insertType) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (first_name, last_name, birthday, sex_id) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), ?) RETURNING id"
                    );
                    preparedStatement.setString(1, person.getFirstName());
                    preparedStatement.setString(2, person.getLastName());
                    preparedStatement.setString(3, person.getBirthday());
                    preparedStatement.setLong(4, person.getSexId());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO person (id, first_name, last_name, birthday, sex_id) " +
                                    "VALUES (?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?)"
                    );
                    preparedStatement.setLong(1, person.getId());
                    preparedStatement.setString(2, person.getFirstName());
                    preparedStatement.setString(3, person.getLastName());
                    preparedStatement.setString(4, person.getBirthday());
                    preparedStatement.setLong(5, person.getSexId());

                }
                preparedStatement.addBatch();

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    person.setId(resultSet.getInt("id"));
                    return person;
                } else {
                    return null;
                }

            } catch (ClassNotFoundException e) {
                return null;
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return null;
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }
}
