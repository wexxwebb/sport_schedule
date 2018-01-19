package db.xml.xmlWrapper;

import db.POJO.Person;
import db.POJO.Sex;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataBaseObjectTest {

    public DataBaseObjectTest() {
    }

    private static DataBaseObject dataBaseObject;

    @Test
    public void setObject() {
        dataBaseObject = new DataBaseObject();
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(25);
        person.setFirstName("Che");
        person.setLastName("Gevara");
        person.setSexId(1);
        person.setBirthday("1944-01-01");
        personList.add(person);
        dataBaseObject.setObject(new PersonTable(personList));

        assertEquals(person, dataBaseObject.getPersonList().get(0));

        Person personOther = new Person();
        personOther.setId(15);
        personOther.setFirstName("Che");
        personOther.setLastName("Gevara");
        personOther.setSexId(1);
        personOther.setBirthday("1944-01-01");

        assertNotEquals(personOther, dataBaseObject.getPersonList().get(0));
    }
}