package db.POJO;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PersonTest {

    public PersonTest() {
    }

    @Test
    public void equals() {
        Person person = new Person();
        person.setId(25);
        person.setFirstName("Che");
        person.setLastName("Gevara");
        person.setSexId(1);
        person.setBirthday("1944-01-01");

        Person person2 = new Person();
        person2.setId(25);
        person2.setFirstName("Che");
        person2.setLastName("Gevara");
        person2.setSexId(1);
        person2.setBirthday("1944-01-01");

        assertTrue(person.equals(person2));
        person2.setSexId(2);
        assertFalse(person.equals(person2));
        System.out.println("Test)) ");
    }
}