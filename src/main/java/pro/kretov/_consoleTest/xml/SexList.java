package pro.kretov._consoleTest.xml;

import pro.kretov.db.POJO.Sex;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "SexTable")
public class SexList {

    private List<Sex> sexList;

    public SexList() {
    }

    public SexList(List<Sex> sexList) {
        this.sexList = sexList;
    }

    public List<Sex> getSexList() {
        return sexList;
    }

    public void setSexList(List<Sex> sexList) {
        this.sexList = sexList;
    }
}
