package db.xml.marshaling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.Callable;

public class TableUnmarshaller implements Callable<UnmarshalingResult> {

    private Path path;
    private Class unmarshClass;

    public TableUnmarshaller(Path path, Class unmarshClass) {
        this.path = path;
        this.unmarshClass = unmarshClass;
    }

    @Override
    public UnmarshalingResult call() {
        try {
            JAXBContext context = JAXBContext.newInstance(unmarshClass);
            File file = path.toFile();
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object object = unmarshaller.unmarshal(file);
            return new UnmarshalingResult(object, unmarshClass, true, "Success");

        } catch (JAXBException e) {
            return new UnmarshalingResult(null, null, false, e.getMessage());
        }


    }
}
