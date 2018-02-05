package db.xml.xmlWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public abstract class Table implements Callable<Boolean> {

    @XmlElement(name = "Listener")
    private Set<String> listenerNameList = new HashSet<>();

    @XmlTransient
    private Set<Table> listenerSet = new HashSet<>();

    @XmlTransient
    protected BlockingQueue<Long> idCanInsert = new ArrayBlockingQueue<Long>(10);

    public boolean isReady() {
        List<String> classNames =
                this.getListenerTables().stream().map(table -> table.getClass().getSimpleName()).collect(Collectors.toList());
        return classNames.containsAll(this.getListenerNameList());
    }

    public void dispatch(long id) {
        listenerSet.forEach((table) -> table.insertId(id));
    }

    public void insertId(long id) {
        idCanInsert.add(id);
    }

    public void addListener(String name) {
        this.listenerNameList.add(name);
    }

    public void addListenerTable(Table table) {
        this.listenerSet.add(table);
    }

    public Set<String> getListenerNameList() {
        return listenerNameList;
    }

    public Set<Table> getListenerTables() {
        return listenerSet;
    }

}
