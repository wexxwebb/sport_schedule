package db.xml;

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
import db.xml.marshaling.TableUnmarshaller;
import db.xml.xmlWrapper.DataBaseObject;

import java.nio.file.Path;
import java.nio.file.Paths;

import static common.InsertType.RESTORE;

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
