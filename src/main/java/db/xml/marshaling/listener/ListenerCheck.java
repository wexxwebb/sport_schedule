package db.xml.marshaling.listener;

import db.xml.xmlWrapper.Table;

import java.util.ArrayList;
import java.util.List;

public class ListenerCheck {

    public boolean check(Table table) {

        List<String> classNames = new ArrayList<>();
        table.getListenerTables().forEach(t -> classNames.add(t.getClass().getSimpleName()));

        return classNames.containsAll(table.getListenerNameList());
    }

}
