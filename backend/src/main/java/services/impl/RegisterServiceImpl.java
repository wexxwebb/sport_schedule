package services.impl;

import common.Logged;
import common.RegisterDataCheck;
import common.Result;
import db.dao._interfaces.PersonDAO;
import db.dao._interfaces.UserDataDAO;
import db.entities.Impl.PersonImpl;
import db.entities.Impl.UserDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._interfaces.RegisterService;
import util.CustomPasswordEncoder;

import java.sql.Date;
import java.util.Map;

import static common.InsertType.NEW;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Logged
    private Logger logger;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private UserDataDAO userDataDAO;

    @Override
    public Result<Map<String, String>> register(String login, String password, String passwordApprove,
                                                String name, String lastName, String sex, String birthday) {

        RegisterDataCheck regCheck = checkData(login, password, passwordApprove, name, lastName, sex, birthday);
        if (regCheck.isValid()) {
            PersonImpl person = new PersonImpl(
//                    name,
//                    lastName,
//                    birthday
            );
            CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
            person = personDAO.insert(person, NEW);
            UserDataImpl userData = new UserDataImpl(
//                    person.getId(),
//                    login,
//                    passwordEncoder.encode(password)
            );
            userDataDAO.insert(userData, NEW);
            logger.info("New registration");
            return new Result<>(null, true, "New registration");
        }

        if (!regCheck.isLogin()) {
            regCheck.getMessages().put("loginError", "Некорректный логин");
        } else regCheck.getMessages().put("login", login);

        if (!regCheck.isPassword()) {
            regCheck.getMessages().put("passwordError", "Пароли не совпадают или пустой пароль");
        } else {
            regCheck.getMessages().put("password", password);
            regCheck.getMessages().put("passwordApprove", passwordApprove);
        }
        if (!regCheck.isName()) {
            regCheck.getMessages().put("nameError", "Имя обязательно");
        } else regCheck.getMessages().put("name", name);
        if (!regCheck.isLastName()) {
            regCheck.getMessages().put("lastNameError", "Фамилия обязательна");
        } else regCheck.getMessages().put("lastName", lastName);
        if (!regCheck.isSex()) {
            regCheck.getMessages().put("sexError", "Выберете пол");
        } else regCheck.getMessages().put("sex", sex);
        if (!regCheck.isBirthday()) {
            regCheck.getMessages().put("dateError", "Некорректная дата");
        } else regCheck.getMessages().put("birthday", birthday);

        logger.info("Registration data denied");
        return new Result<>(regCheck.getMessages(), false, "Registration data denied");
    }

    private RegisterDataCheck checkData(
            String login, String password, String passwordApprove,
            String name, String lastName, String sex, String birthday) {

        RegisterDataCheck regCheck = new RegisterDataCheck();

        if (login != null && !login.isEmpty())
            if (userDataDAO.getByLogin(login) != null) regCheck.setLogin(true);

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
