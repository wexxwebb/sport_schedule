package services._inter;

import db.entities.Impl.PersonImpl;
import db.entities.Impl.UserDataImpl;
import services.excep.ServiceIsNotAvailableException;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {

    List<PersonImpl> getUserList() throws ServiceIsNotAvailableException;

    UserDataImpl getUserByLogin(String login) throws ServiceIsNotAvailableException;
}
