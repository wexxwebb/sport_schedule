package db.xml.marshaling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.concurrent.Callable;

public class TableMarshaller implements Callable<MarshallingResult> {

    private Path path;
    private JAXBContext context;
    private Class marshClass;
    private Object object;

    public TableMarshaller(Path path, Object object) throws JAXBException {
        this.path = path;
        this.marshClass = object.getClass();
        this.context = JAXBContext.newInstance(marshClass);
        this.object = object;
    }

    @Override
    public MarshallingResult call() {
        int retry = 0;
        while (true) {
            try {

                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(object, new FileOutputStream(path.toFile()));

                return new MarshallingResult(path, marshClass, true, "Success");

            } catch (JAXBException e) {
                return new MarshallingResult(null, null, false, e.getMessage());
            } catch (FileNotFoundException e) {
                retry++;
                if (retry > 5) {
                    return new MarshallingResult(null, null, false, e.getMessage());
                }
            }
        }
    }
}
