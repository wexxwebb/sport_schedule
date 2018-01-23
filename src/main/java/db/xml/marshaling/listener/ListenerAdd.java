package db.xml.marshaling.listener;

import db.xml.xmlWrapper.Table;

import java.util.List;

public class ListenerAdd {

    List<Table> tables;

    public ListenerAdd(List<Table> tables) {
        this.tables = tables;
    }

    public void add(Table table) {
        for (Table tab : tables) {
            for (String name : tab.getListenerNameList()) {
                if (name.equals(table.getClass().getSimpleName())) {
                    tab.addListenerTable(table);
                }
            }
        }
    }
}
