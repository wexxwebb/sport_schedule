package services.impl;

import common.RegisterDataCheck;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.Person;
import db.pojo.UserData;
import db.connectionManager.ConnectionManagerImpl;
import org.apache.log4j.Logger;
import services.RegisterService;

import static common.InsertType.NEW;

public class RegisterServiceImpl implements RegisterService {

    private Logger logger = Logger.getLogger(RegisterServiceImpl.class);

    private UserDataDAO userDataDAO = new UserDataDAOImpl();

    private PersonDAO personDAO = new PersonDAOImpl(ConnectionManagerImpl.getInstance());

    public UserDataDAO getUserDataDAO() {
        return userDataDAO;
    }

    public void setUserDataDAO(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public RegisterDataCheck register(
            String login, String password, String passwordApprove, String name,
            String lastName, String sex, String birthday) {

        RegisterDataCheck regCheck = checkData(login, password, passwordApprove, name, lastName, sex, birthday);
        if (regCheck.isValid()) {
            Person person = new Person(
                    name,
                    lastName,
                    birthday,
                    Integer.parseInt(sex)
            );
            person = personDAO.insert(person, NEW).get();
            logger.info(person);
            UserData userData = new UserData(
                    person.getId(),
                    login,
                    password,
                    1
            );
            userDataDAO.insert(userData, NEW);
            logger.info("New registration");
        } else {
            logger.info("Registration data denied");
        }
        return regCheck;
    }

    private RegisterDataCheck checkData(
            String login, String password, String passwordApprove,
            String name, String lastName, String sex, String birthday) {

        RegisterDataCheck regCheck = new RegisterDataCheck();

        if (login != null && !login.isEmpty()) if (!userDataDAO.getByLogin(login).isSuccess()) regCheck.setLogin(true);

        if (password != null && passwordApprove != null && !password.isEmpty() && !passwordApprove.isEmpty())
            if (password.equals(passwordApprove)) regCheck.setPassword(true);

        if (name != null && !name.isEmpty()) regCheck.setName(true);

        if (lastName != null && !lastName.isEmpty()) regCheck.setLastName(true);

        if (sex != null && !sex.isEmpty() && sex.matches("[0-9]+")) regCheck.setSex(true);

        if (birthday != null && !birthday.isEmpty()
                && birthday.matches("[0-9]{4}-[0-1][0-9]-[0-3][0-9]")) regCheck.setBirthday(true);
        else logger.error(birthday);

        return regCheck;
    }
}
