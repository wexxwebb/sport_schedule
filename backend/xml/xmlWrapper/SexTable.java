package db.xml.xmlWrapper;

import common.Log;
import db.dao._interfaces.SexDAO;
import db.dao.jdbc.SexDAOImpl;
import db.entities.inter.Sex;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.util.*;


@XmlRootElement(name = "SexTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class SexTable extends Table {

    @XmlElement(name = "Sex")
    private List<Sex> sexList;

    @XmlTransient
    private SexDAO sexDAO = new SexDAOImpl();

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
                Long id = idCanInsert.take();
                for (Sex sex : sexList) {
                    if (sex.getId() == id) {
                        sexDAO.insert(sex);
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
