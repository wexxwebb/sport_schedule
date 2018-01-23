package db.xml.xmlWrapper;

import db.pojo.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonTable {

    @XmlElement(name = "Person")
    private List<Person> personList;

    public PersonTable() {
    }

    public PersonTable(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (this == obj) return true;
            if (!(obj instanceof PersonTable)) return false;
            if (this.personList.equals(((PersonTable) obj).personList)) return true;
        }
        return false;
    }
}
