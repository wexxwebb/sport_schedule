package db.xml;

import db.dao.SexDAO;
import db.dao.jdbc.SexDAOImpl;
import db.dao.UserDataDAO;
import db.dao.jdbc.UserDataDAOImpl;
import db.xml.marshaling.MarshallingResult;
import db.xml.marshaling.TableMarshaller;
import db.xml.marshaling.TableUnmarshaller;
import db.xml.xmlWrapper.DataBaseObject;
import db.xml.xmlWrapper.SexTable;
import db.xml.xmlWrapper.Table;
import db.xml.xmlWrapper.UserDataTable;

import javax.xml.bind.JAXBException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class Marshalling {

    private static final String dir = "_xml/";
    private static final String extension = ".xml";

    private static Path getPath(String string) {
        return Paths.get(dir + string + extension);
    }

    private static void threadStart(Object object, ExecutorService execServ, BlockingQueue<Future<MarshallingResult>> futures) {
        try {
            Future<MarshallingResult> futureMarsh =
                    execServ.submit(new TableMarshaller(getPath(object.getClass().getSimpleName()), object));
            futures.add(futureMarsh);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JAXBException {

        ExecutorService execService = Executors.newFixedThreadPool(8);
        BlockingQueue<Future<MarshallingResult>> futureList = new ArrayBlockingQueue<>(8);


        SexDAO sexDAO =
                new SexDAOImpl();
        Table sexTable = new SexTable(sexDAO.getAll().get());
        sexTable.addListener("UserDataTable");
        threadStart(sexTable, execService, futureList);


//        PersonDAO personDAO =
//                new PersonDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new PersonTable(personDAO.getAll().get()), execService, futureList);
//
//        StateDAO stateDAO =
//                new StateDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new StateTable(stateDAO.getAll().get()), execService, futureList);
//
        UserDataDAO userDataDAO =
                new UserDataDAOImpl();
        Table userDataTable = new UserDataTable(userDataDAO.getAll().get());
        threadStart(userDataTable, execService, futureList);
//
//        AdminDAO adminDAO =
//                new AdminDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new AdminDataTable(adminDAO.getAll().get()),
//                execService,
//                futureList
//        );
//
//        TrainingDAO trainingDAO =
//                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new TrainingTable(trainingDAO.getAll().get()),
//                execService,
//                futureList
//        );
//
//        ExerciseDAO exerciseDAO =
//                new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new ExerciseTable(exerciseDAO.getAll().get()),
//                execService,
//                futureList
//        );
//
//        ExerciseDataDAO exerciseDataDAO =
//                new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
//        threadStart(
//                new ExerciseDataTable(exerciseDataDAO.getAll().get()),
//                execService,
//                futureList
//        );

        DataBaseObject dataBase = new DataBaseObject();

        boolean stop = false;
        while (!stop) {
            stop = true;
            for (Future<MarshallingResult> future : futureList) {
                if (!future.isDone()) {
                    stop = false;
                } else {
                    try {
                        if (future.get().isSuccess()) {
                            formatAndPrint(future.get());
                            TableUnmarshaller tableUnmarshaller =
                                    new TableUnmarshaller(future.get().getPath(), future.get().getaClass());
                            dataBase.setObject(tableUnmarshaller.call().getObject());
                        }
                        futureList.remove(future);
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        futureList.remove(future);
                    }
                }
            }
        }
        execService.shutdown();

        ExecutorService exec = Executors.newSingleThreadExecutor();

        Future<MarshallingResult> future =
                exec.submit(
                        new TableMarshaller(
                                getPath("dataBaseXML/" + dataBase.getClass().getSimpleName()),
                                dataBase
                        )
                );

        synchronized (Marshalling.class) {
            try {
                Marshalling.class.wait();
            } catch (InterruptedException e) {
                System.out.println("Exit whit error");
                return;
            }
        }

        exec.shutdown();

        try {
            System.out.println(
                    String.format(
                            "\nDatabase marshaled to %s successful",
                            future.get().getPath().getFileName()
                    )
            );

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exit whit error");
        }

    }

    private static void formatAndPrint(MarshallingResult result) {
        System.out.println(
                String.format(
                        "%s marshaled to %s successful",
                        result.getaClass().getSimpleName(),
                        result.getPath().getFileName()
                )
        );
    }
}
