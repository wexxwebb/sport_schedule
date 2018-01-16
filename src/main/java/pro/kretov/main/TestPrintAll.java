package pro.kretov.main;

import pro.kretov.db.DAO.admin.AdminDAO;
import pro.kretov.db.DAO.admin.AdminDAOImpl;
import pro.kretov.db.DAO.exercise.ExerciseDAO;
import pro.kretov.db.DAO.exercise.ExerciseDAOImpl;
import pro.kretov.db.DAO.exerciseData.ExerciseDataDAO;
import pro.kretov.db.DAO.exerciseData.ExerciseDataDAOImpl;
import pro.kretov.db.DAO.person.PersonDAO;
import pro.kretov.db.DAO.person.PersonDAOImpl;
import pro.kretov.db.DAO.sex.SexDAO;
import pro.kretov.db.DAO.sex.SexDAOImpl;
import pro.kretov.db.DAO.state.StateDAO;
import pro.kretov.db.DAO.state.StateDAOImpl;
import pro.kretov.db.DAO.training.TrainingDAO;
import pro.kretov.db.DAO.training.TrainingDAOImpl;
import pro.kretov.db.DAO.user.UserDataDAO;
import pro.kretov.db.DAO.user.UserDataDAOImpl;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

public class TestPrintAll {
    public static void main(String[] args) {
        System.out.println("Sex");
        SexDAO sexDAO =
                new SexDAOImpl(ConnectionManagerImpl.getInstance());
        sexDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("Person");
        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());
        personDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("State");
        StateDAO stateDAO =
                new StateDAOImpl(ConnectionManagerImpl.getInstance());
        stateDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("UserData");
        UserDataDAO userDataDAO =
                new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
        userDataDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("Admin");
        AdminDAO adminDAO =
                new AdminDAOImpl(ConnectionManagerImpl.getInstance());
        adminDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("Training");
        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        trainingDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("Exercise");
        ExerciseDAO exerciseDAO = new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());
        exerciseDAO.getAll().getResult().forEach(
                System.out::println
        );

        System.out.println("ExerciseData");
        ExerciseDataDAO exerciseDataDAO = new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
        exerciseDataDAO.getAll().getResult().forEach(
                System.out::println
        );
    }
}
