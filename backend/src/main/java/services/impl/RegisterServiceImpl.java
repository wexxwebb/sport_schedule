package services.impl;

import common.Logged;
import common.Result;
import common.SDF;
import db.dao._inter.PersonDAO;
import db.dao._inter.UserDataDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.PersonImpl;
import db.entities.Impl.SexImpl;
import db.entities.Impl.UserDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services._inter.RegisterService;
import services.excep.ServiceIsNotAvailableException;
import services.utils.RegisterDataCheck;

import java.sql.Date;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Logged
    private Logger logger;

    private PersonDAO personDAO;

    private UserDataDAO userDataDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterServiceImpl(PersonDAO personDAO, UserDataDAO userDataDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.userDataDAO = userDataDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result<Map<String, String>> register(String login, String password, String passwordApprove,
                                                String firstName, String lastName, String sex, String birthday) throws ServiceIsNotAvailableException {

        RegisterDataCheck regCheck = checkData(login, password, passwordApprove, firstName, lastName, sex, birthday);
        if (regCheck.isValid()) {
            SexImpl sexImpl = new SexImpl(Long.parseLong(sex));
            PersonImpl person = new PersonImpl(firstName, lastName,
                    new Date(SDF.getDate(birthday).get().getTime()), sexImpl);

            UserDataImpl userData = new UserDataImpl(person, login, passwordEncoder.encode(password),
                    new Date((new java.util.Date()).getTime()), true, "ROLE_USER");

            person.setUserData(userData);
            try {
                personDAO.insert(person);
                userDataDAO.insert(userData);
            } catch (DataIsNotAvailableException e) {
                logger.error(e);
                throw new ServiceIsNotAvailableException(e);
            }
            logger.debug("New registration");
            return new Result<>(null, true, "New registration");
        }
        if (!regCheck.isLogin()) {
            regCheck.getMessages().put("loginError", "Некорректный логин");
        } else {
            regCheck.getMessages().put("login", login);
        }

        if (!regCheck.isPassword()) {
            regCheck.getMessages().put("passwordError", "Пароли не совпадают или пустой пароль");
        } else {
            regCheck.getMessages().put("password", password);
            regCheck.getMessages().put("passwordApprove", passwordApprove);
        }
        if (!regCheck.isName()) {
            regCheck.getMessages().put("firstNameError", "Имя обязательно");
        } else {
            regCheck.getMessages().put("firstName", firstName);
        }
        if (!regCheck.isLastName()) {
            regCheck.getMessages().put("lastNameError", "Фамилия обязательна");
        } else {
            regCheck.getMessages().put("lastName", lastName);
        }
        if (!regCheck.isSex()) {
            regCheck.getMessages().put("sexError", "Выберете пол");
        } else {
            regCheck.getMessages().put("sex", sex);
        }
        if (!regCheck.isBirthday()) {
            regCheck.getMessages().put("dateError", "Некорректная дата");
        } else {
            regCheck.getMessages().put("birthday", birthday);
        }
        logger.debug("Registration data denied");
        return new Result<>(regCheck.getMessages(), false, "Registration data denied");
    }

    private RegisterDataCheck checkData(
            String login, String password, String passwordApprove,
            String name, String lastName, String sex, String birthday) throws ServiceIsNotAvailableException {

        RegisterDataCheck regCheck = new RegisterDataCheck();
        if (login != null && !login.isEmpty()) {
            try {
                if (userDataDAO.getByLogin(login) == null) {
                    regCheck.setLogin(true);
                }
            } catch (DataIsNotAvailableException e) {
                logger.error(e);
                throw new ServiceIsNotAvailableException(e);
            }
        }
        if (password != null && passwordApprove != null && !password.isEmpty() && !passwordApprove.isEmpty()) {
            if (password.equals(passwordApprove)) {
                regCheck.setPassword(true);
            }
        }
        if (name != null && !name.isEmpty()) {
            regCheck.setName(true);
        }
        if (lastName != null && !lastName.isEmpty()) {
            regCheck.setLastName(true);
        }
        if (sex != null && !sex.isEmpty() && sex.matches("[0-9]+")) {
            regCheck.setSex(true);
        }
        if (birthday != null && !birthday.isEmpty()
                && birthday.matches("[0-9]{4}-[0-1][0-9]-[0-3][0-9]")) {
            regCheck.setBirthday(true);
        }
        return regCheck;
    }
}
