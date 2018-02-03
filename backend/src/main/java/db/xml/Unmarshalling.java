package db.xml;

import db.dao.AdminDAO;
import db.dao.jdbc.AdminDAOImpl;
import db.dao.ExerciseDAO;
import db.dao.jdbc.ExerciseDAOImpl;
import db.dao.ExerciseDataDAO;
import db.dao.jdbc.ExerciseDataDAOImpl;
import db.dao.PersonDAO;
import db.dao.jdbc.PersonDAOImpl;
import db.dao.SexDAO;
import db.dao.jdbc.SexDAOImpl;
import db.dao.StateDAO;
import db.dao.jdbc.StateDAOImpl;
import db.dao.TrainingDAO;
import db.dao.jdbc.TrainingDAOImpl;
import db.dao.UserDataDAO;
import db.dao.jdbc.UserDataDAOImpl;
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
                new SexDAOImpl();
        dataBaseObject.getSexList().forEach(
                sex -> sexDAO.insert(sex, RESTORE)
        );

        PersonDAO personDAO =
                new PersonDAOImpl();
        dataBaseObject.getPersonList().forEach(
                person -> personDAO.insert(person, RESTORE)
        );

        StateDAO stateDAO =
                new StateDAOImpl();
        dataBaseObject.getStateList().forEach(
                state -> stateDAO.insert(state, RESTORE)
        );

        UserDataDAO userDataDAO =
                new UserDataDAOImpl();
        dataBaseObject.getUserDataList().forEach(
                userData -> userDataDAO.insert(userData, RESTORE)
        );

        AdminDAO adminDAO =
                new AdminDAOImpl();
        dataBaseObject.getAdminDataList().forEach(
                adminData -> adminDAO.insert(adminData, RESTORE)
        );

        TrainingDAO trainingDAO =
                new TrainingDAOImpl();
        dataBaseObject.getTrainingList().forEach(
                training -> trainingDAO.insert(training, RESTORE)
        );

        ExerciseDAO exerciseDAO =
                new ExerciseDAOImpl();
        dataBaseObject.getExerciseList().forEach(
                exercise -> exerciseDAO.insert(exercise, RESTORE)
        );

        ExerciseDataDAO exerciseDataDAO =
                new ExerciseDataDAOImpl();
        dataBaseObject.getExerciseDataList().forEach(
                exerciseData -> exerciseDataDAO.insert(exerciseData, RESTORE)
        );
    }
}
