package db.xml.xmlWrapper;

import db.POJO.Sex;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "SexTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class SexTable {

    @XmlElement(name = "Sex")
    private List<Sex> sexList;

    public SexTable() {
    }

    public SexTable(List<Sex> sexList) {
        this.sexList = sexList;
    }

    public List<Sex> getSexList() {
        return sexList;
    }

    public void setSexList(List<Sex> sexList) {
        this.sexList = sexList;
    }
}
