package db.xml.table;

import db.xml.xmlWrapper.Table;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TableImpl implements Table {

    @XmlElement(name = "row")
    List list;

    @XmlElement(name = "listener")
    Set<String> listenerNames;

    @XmlTransient
    Set<Table> listeners;

    public TableImpl() {
    }

    public TableImpl(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
