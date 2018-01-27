package db.xml.xmlWrapper;

import common.Log;
import db.dao.sex.SexDAO;
import db.dao.sex.SexDAOImpl;
import db.pojo.Sex;
import db.connectionManager.ConnectionManagerImpl;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static common.InsertType.RESTORE;


@XmlRootElement(name = "SexTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class SexTable extends Table {

    @XmlElement(name = "Sex")
    private List<Sex> sexList;

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
        while (idCanInsert.isEmpty()) {
            try {
                int id = idCanInsert.take();
                for (Sex sex : sexList) {
                    if (sex.getId() == id) {
                        sexDAO.insert(sex, RESTORE);
                    }
                }
            } catch (InterruptedException e) {
                logger.error(new Log(e, this));
            }
        }
        logger.info("All rows inserted");
        return true;
    }

    public List<Sex> getSexList() {
        return sexList;
    }

    public void setSexList(List<Sex> sexList) {
        this.sexList = sexList;
    }
}
