package services.impl;

import common.Logged;
import db.dao._inter.PersonDAO;
import db.dao._inter.UserDataDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.PersonImpl;
import db.entities.Impl.UserDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services._inter.UserService;
import services.excep.ServiceIsNotAvailableException;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Logged
    private Logger logger;

    private UserDataDAO userDataDAO;

    private PersonDAO personDAO;

    @Autowired
    public UserServiceImpl(UserDataDAO userDataDAO, PersonDAO personDAO) {
        this.userDataDAO = userDataDAO;
        this.personDAO = personDAO;
    }

    public List<PersonImpl> getUserList() throws ServiceIsNotAvailableException {
        try {
            return personDAO.getAll();
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public UserDataImpl getUserByLogin(String login) throws ServiceIsNotAvailableException {
        try {
            return userDataDAO.getByLogin(login);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

}
