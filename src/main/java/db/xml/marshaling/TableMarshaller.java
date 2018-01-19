package db.xml.marshaling;

import db.xml.Marshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        String message = "";
        int retry = 0;
        while (true) {
            try {
                FileOutputStream fos = new FileOutputStream(path.toFile());
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(object, fos);
                int retryClose = 0;
                while (true) {
                    try {
                        fos.close();
                        break;
                    } catch (IOException e) {
                        retryClose++;
                        if (retryClose > 5) {
                            message = String.format("; Closing error %s", path.getFileName());
                        }
                    }
                }

                synchronized (Marshalling.class) {
                    Marshalling.class.notify();
                }

                return new MarshallingResult(path, marshClass, true, "Success" + message);

            } catch (JAXBException e) {
                return new MarshallingResult(
                        null,
                        null,
                        false,
                        e.getMessage() + message
                );
            } catch (FileNotFoundException e) {
                retry++;
                if (retry > 5) {
                    return new MarshallingResult(
                            null,
                            null,
                            false,
                            e.getMessage() + message
                    );
                }
            }
        }
    }
}
