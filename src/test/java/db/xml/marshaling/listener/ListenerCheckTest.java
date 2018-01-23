package db.xml.marshaling.listener;

import db.xml.marshaling.listener.ListenerCheck;
import db.xml.xmlWrapper.SexTable;
import db.xml.xmlWrapper.Table;
import db.xml.xmlWrapper.UserDataTable;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ListenerCheckTest {

    @Test
    public void check() {
        ListenerCheck listenerCheck = new ListenerCheck();
        List<String> names = new ArrayList<>();

        Table table = new SexTable();

        table.addListener("SexTable");
        table.addListener("UserDataTable");

        List<Table> tables = new ArrayList<>();

        table.addListenerTable(new SexTable());
        table.addListenerTable(new UserDataTable());

        assertTrue(listenerCheck.check(table));

        table.getListenerTables().remove(0);

        assertFalse(listenerCheck.check(table));
    }
}