package db.xml.xmlWrapper;

import db.dao._inter.UserDataDAO;
import db.dao.jdbc.UserDataDAOImpl;
import db.entities._inter.UserData;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDataTable extends Table {

    @XmlElement(name = "UserData")
    List<UserData> userDataList;

    @XmlTransient
    private UserDataDAO userDataDAO = new UserDataDAOImpl();

    @XmlTransient
    private Logger logger = Logger.getLogger(db.entities.Impl.UserDataImpl.class);

    public UserDataTable() {
    }

    public UserDataTable(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @Override
    public Boolean call() throws Exception {
//        for (UserData userData : userDataList) {
//            if (userDataDAO.insert(userData, RESTORE).isSuccess()) {
//                dispatch(userData.getId());
//            }
//        }
//        logger.info("All rows inserted");
        return true;
    }
}
