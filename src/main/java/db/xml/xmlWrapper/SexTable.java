package db.xml.xmlWrapper;

import db.DAO.sex.SexDAO;
import db.DAO.sex.SexDAOImpl;
import db.POJO.Sex;
import db.connectionManager.ConnectionManagerImpl;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static common.InsertType.RESTORE;


@XmlRootElement(name = "SexTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class SexTable implements Table {

    @XmlElement(name = "Sex")
    private List<Sex> sexList;

    @XmlElement(name = "Listener")
    private Set<String> listenerNameList = new HashSet<>();

    @XmlTransient
    private Set<Table> listenerSet = new HashSet<>();

    @XmlTransient
    private BlockingQueue<Integer> idCanInsert = new ArrayBlockingQueue<>(10);

    @XmlTransient
    private SexDAO sexDAO = new SexDAOImpl(ConnectionManagerImpl.getInstance());

    @XmlTransient
    private Logger logger = Logger.getLogger(SexTable.class);


    public SexTable() {
    }

    public SexTable(List<Sex> sexList) {
        this.sexList = sexList;
    }

    @Override
    public Boolean call() {
        for (Sex sex : sexList) {
            if (sexDAO.insert(sex, RESTORE).isSuccess()) {
                dispach(sex.getId());
            }
        }
        return true;
    }

    public void dispach(int id) {
        listenerSet.forEach((table) -> table.insertId(id));
    }

    @Override
    public void insertId(int id) {
        idCanInsert.add(id);
    }

    @Override
    public void addListener(String name) {
        this.listenerNameList.add(name);
    }

    @Override
    public void addListenerTable(Table table) {
        this.listenerSet.add(table);
    }

    public Set<String> getListenerNameList() {
        return listenerNameList;
    }

    @Override
    public Set<Table> getListenerTables() {
        return listenerSet;
    }

    public List<Sex> getSexList() {
        return sexList;
    }

    public void setSexList(List<Sex> sexList) {
        this.sexList = sexList;
    }
}
