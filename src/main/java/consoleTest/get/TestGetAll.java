package consoleTest.get;

import db.dao.admin.AdminDAO;
import db.dao.admin.AdminDAOImpl;
import db.dao.exercise.ExerciseDAO;
import db.dao.exercise.ExerciseDAOImpl;
import db.dao.exerciseData.ExerciseDataDAO;
import db.dao.exerciseData.ExerciseDataDAOImpl;
import db.dao.person.PersonDAO;
import db.dao.person.PersonDAOImpl;
import db.dao.sex.SexDAO;
import db.dao.sex.SexDAOImpl;
import db.dao.state.StateDAO;
import db.dao.state.StateDAOImpl;
import db.dao.training.TrainingDAO;
import db.dao.training.TrainingDAOImpl;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
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
