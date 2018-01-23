package db.xml.marshaling.listener;

import db.xml.xmlWrapper.SexTable;
import db.xml.xmlWrapper.Table;
import db.xml.xmlWrapper.UserDataTable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListenerAddTest {

    @Test
    public void add() {

        List<Table> tableList = new ArrayList<>();

        SexTable sexTable = new SexTable();
        UserDataTable userDataTable = new UserDataTable();
        sexTable.addListener("SexTable");
        sexTable.addListener("UserDataTable");

        tableList.add(sexTable);

        ListenerAdd listenerAdd = new ListenerAdd(tableList);

        listenerAdd.add(sexTable);
        listenerAdd.add(userDataTable);

        List<Table> testTableList = new ArrayList<>();
        testTableList.add(sexTable);
        testTableList.add(userDataTable);

        assertTrue(tableList.get(0).getListenerTables().containsAll(testTableList));
    }
}