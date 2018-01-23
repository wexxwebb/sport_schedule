package db.xml.xmlWrapper;

import java.util.Set;
import java.util.concurrent.Callable;

public interface Table extends Callable<Boolean> {

    void insertId(int id);

    void dispach(int id);

    void addListener(String name);

    void addListenerTable(Table table);

    Set<String> getListenerNameList();

    Set<Table> getListenerTables();
}
