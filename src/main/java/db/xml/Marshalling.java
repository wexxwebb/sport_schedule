package db.xml;

import db.DAO.admin.AdminDAO;
import db.DAO.admin.AdminDAOImpl;
import db.DAO.exercise.ExerciseDAO;
import db.DAO.exercise.ExerciseDAOImpl;
import db.DAO.exerciseData.ExerciseDataDAO;
import db.DAO.exerciseData.ExerciseDataDAOImpl;
import db.DAO.person.PersonDAO;
import db.DAO.person.PersonDAOImpl;
import db.DAO.sex.SexDAO;
import db.DAO.sex.SexDAOImpl;
import db.DAO.state.StateDAO;
import db.DAO.state.StateDAOImpl;
import db.DAO.training.TrainingDAO;
import db.DAO.training.TrainingDAOImpl;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.connectionManager.ConnectionManagerImpl;
import db.xml.marshaling.MarshallingResult;
import db.xml.marshaling.TableMarshaller;
import db.xml.marshaling.TableUnmarshaller;
import db.xml.xmlWrapper.*;

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
            futures.add(
                    execServ.submit(
                            new TableMarshaller(
                                    getPath(object.getClass().getSimpleName()), object
                            )
                    )
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JAXBException {

        ExecutorService execService = Executors.newFixedThreadPool(8);
        BlockingQueue<Future<MarshallingResult>> futureList = new ArrayBlockingQueue<>(8);

        SexDAO sexDAO =
                new SexDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new SexTable(sexDAO.getAll().getResult()),
                execService,
                futureList
        );

        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new PersonTable(personDAO.getAll().getResult()),
                execService,
                futureList
        );

        StateDAO stateDAO =
                new StateDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new StateTable(stateDAO.getAll().getResult()),
                execService,
                futureList
        );

        UserDataDAO userDataDAO =
                new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new UserDataTable(userDataDAO.getAll().getResult()),
                execService,
                futureList
        );

        AdminDAO adminDAO =
                new AdminDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new AdminDataTable(adminDAO.getAll().getResult()),
                execService,
                futureList
        );

        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new TrainingTable(trainingDAO.getAll().getResult()),
                execService,
                futureList
        );

        ExerciseDAO exerciseDAO =
                new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new ExerciseTable(exerciseDAO.getAll().getResult()),
                execService,
                futureList
        );

        ExerciseDataDAO exerciseDataDAO =
                new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
        threadStart(
                new ExerciseDataTable(exerciseDataDAO.getAll().getResult()),
                execService,
                futureList
        );

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
                            dataBase.setObject(tableUnmarshaller.getResult().getObject());
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
                                getPath(dataBase.getClass().getSimpleName()),
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
        //while (!future.isDone());

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
