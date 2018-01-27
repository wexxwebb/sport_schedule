package db.xml.marshaling;

import db.pojo.Person;
import db.xml.xmlWrapper.PersonTable;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TableMarshallerTest {

    @Test
    public void call() throws JAXBException, InterruptedException, IOException {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(25);
        person.setFirstName("Che");
        person.setLastName("Gevara");
        person.setSexId(1);
        person.setBirthday("1944-01-01");
        personList.add(person);

        PersonTable personTable = new PersonTable(personList);
        Path path = Paths.get("src/test/xmlTest.xml");

        TableMarshaller tableMarshaller = new TableMarshaller(path, personTable);
        MarshallingResult result = tableMarshaller.call();
        if (result.isSuccess()) {
            TableUnmarshaller tableUnmarshaller = new TableUnmarshaller(path, PersonTable.class);
            UnmarshalingResult unmarshalingResult = tableUnmarshaller.call();
            if (unmarshalingResult.isSuccess()) {
                PersonTable personTableUnmarsh = (PersonTable) unmarshalingResult.getObject();

                assertEquals(personTable, personTableUnmarsh);

                Thread.sleep(100);

                Files.delete(path);

            }
        }
    }
}