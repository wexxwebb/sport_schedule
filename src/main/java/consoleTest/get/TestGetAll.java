package consoleTest.get;

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

public class TestGetAll {
    public static void main(String[] args) {
        System.out.println("Sex");
        SexDAO sexDAO =
                new SexDAOImpl(ConnectionManagerImpl.getInstance());
        sexDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("Person");
        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());
        personDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("State");
        StateDAO stateDAO =
                new StateDAOImpl(ConnectionManagerImpl.getInstance());
        stateDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("UserData");
        UserDataDAO userDataDAO =
                new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
        userDataDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("Admin");
        AdminDAO adminDAO =
                new AdminDAOImpl(ConnectionManagerImpl.getInstance());
        adminDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("Training");
        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        trainingDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("Exercise");
        ExerciseDAO exerciseDAO = new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());
        exerciseDAO.getAll().get().forEach(
                System.out::println
        );

        System.out.println("ExerciseData");
        ExerciseDataDAO exerciseDataDAO = new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
        exerciseDataDAO.getAll().get().forEach(
                System.out::println
        );
    }
}
