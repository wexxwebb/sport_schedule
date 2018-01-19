package db.xml.xmlWrapper;

import db.POJO.*;

import javax.xml.bind.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataBaseObject {

    @XmlElement(name = "Sex")
    private List<Sex> sexList;
    @XmlElement(name = "Person")
    private List<Person> personList;
    @XmlElement(name = "State")
    private List<State> stateList;
    @XmlElement(name = "UserData")
    private List<UserData> userDataList;
    @XmlElement(name = "AdminData")
    private List<AdminData> adminDataList;
    @XmlElement(name = "Training")
    private List<Training> trainingList;
    @XmlElement(name = "Exercise")
    private List<Exercise> exerciseList;
    @XmlElement(name = "ExerciseData")
    private List<ExerciseData> exerciseDataList;

    public DataBaseObject() {

    }

    public List<Sex> getSexList() {
        return sexList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public List<AdminData> getAdminDataList() {
        return adminDataList;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<ExerciseData> getExerciseDataList() {
        return exerciseDataList;
    }

    public boolean setObject(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            for (Field thisField : this.getClass().getDeclaredFields()) {
                if (field.getGenericType().getTypeName().equals(thisField.getGenericType().getTypeName())) {
                    for (Method method : object.getClass().getMethods()) {
                        if (method.getGenericReturnType().getTypeName().equals(field.getGenericType().getTypeName())) {
                            try {
                                thisField.set(this, method.invoke(object, (Object[]) null));
                                return true;
                            } catch (IllegalAccessException e) {
                                return false;
                            } catch (InvocationTargetException e) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
