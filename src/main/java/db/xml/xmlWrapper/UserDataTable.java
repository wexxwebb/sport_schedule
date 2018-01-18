package db.xml.xmlWrapper;

import db.POJO.UserData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UserDataTable {

    List<UserData> userDataList;

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
}
