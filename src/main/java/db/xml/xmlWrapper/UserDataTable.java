package db.xml.xmlWrapper;

import db.POJO.UserData;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDataTable implements Table {

    @XmlElement(name = "UserData")
    List<UserData> userDataList;

    @XmlElement(name = "Listener")
    private Set<String> listenerNameList = new HashSet<>();

    @XmlTransient
    private Set<Table> listenerSet = new HashSet<>();

    @XmlTransient
    private BlockingQueue<Integer> idCanInsert = new ArrayBlockingQueue<>(10);

    @XmlTransient
    private Logger logger = Logger.getLogger(UserData.class);

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
    public Set<String> getListenerNameList() {
        return new HashSet<>();
    }

    @Override
    public Set<Table> getListenerTables() {
        return null;
    }

    @Override
    public void insertId(int id) {

    }

    @Override
    public void dispach(int id) {

    }

    @Override
    public void addListener(String name) {

    }

    @Override
    public void addListenerTable(Table table) {

    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}
