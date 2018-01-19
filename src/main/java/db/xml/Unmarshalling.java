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
import db.xml.marshaling.TableUnmarshaller;
import db.xml.xmlWrapper.DataBaseObject;

import java.nio.file.Path;
import java.nio.file.Paths;

import static common.PersistType.RESTORE;

public class Unmarshalling {

    public static void main(String[] args) {
        Path path = Paths.get("_xml/databaseXML/DataBaseObject.xml");
        TableUnmarshaller tableUnmarshaller = new TableUnmarshaller(path, DataBaseObject.class);
        DataBaseObject dataBaseObject = (DataBaseObject) tableUnmarshaller.call().getObject();

        SexDAO sexDAO =
                new SexDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getSexList().forEach(
                sex -> sexDAO.insert(sex, RESTORE)
        );

        PersonDAO personDAO =
                new PersonDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getPersonList().forEach(
                person -> personDAO.insert(person, RESTORE)
        );

        StateDAO stateDAO =
                new StateDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getStateList().forEach(
                state -> stateDAO.insert(state, RESTORE)
        );

        UserDataDAO userDataDAO =
                new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getUserDataList().forEach(
                userData -> userDataDAO.insert(userData, RESTORE)
        );

        AdminDAO adminDAO =
                new AdminDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getAdminDataList().forEach(
                adminData -> adminDAO.insert(adminData, RESTORE)
        );

        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getTrainingList().forEach(
                training -> trainingDAO.insert(training, RESTORE)
        );

        ExerciseDAO exerciseDAO =
                new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getExerciseList().forEach(
                exercise -> exerciseDAO.insert(exercise, RESTORE)
        );

        ExerciseDataDAO exerciseDataDAO =
                new ExerciseDataDAOImpl(ConnectionManagerImpl.getInstance());
        dataBaseObject.getExerciseDataList().forEach(
                exerciseData -> exerciseDataDAO.insert(exerciseData, RESTORE)
        );
    }
}
