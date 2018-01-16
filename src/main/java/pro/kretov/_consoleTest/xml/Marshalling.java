package pro.kretov._consoleTest.xml;

import pro.kretov.db.DAO.sex.SexDAO;
import pro.kretov.db.DAO.sex.SexDAOImpl;
import pro.kretov.db.POJO.Sex;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Marshalling {
    public static void main(String[] args) {
        SexDAO sexDAO = new SexDAOImpl(ConnectionManagerImpl.getInstance());
        SexList sexList = new SexList(sexDAO.getAll().getResult());
        try {
            JAXBContext context = JAXBContext.newInstance(SexList.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(sexList, new FileOutputStream("_xml/sex.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
