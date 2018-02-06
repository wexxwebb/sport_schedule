package db.entities._inter;

import db.entities.Impl.ExerciseImpl;
import db.entities.Impl.UserDataImpl;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

public interface Training extends Serializable {

    UserDataImpl getUserData();

    void setUserData(UserDataImpl userData);

    long getId();

    void setId(long id);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    Date getTrainingDate();

    void setTrainingDate(Date trainingDate);

    Collection<ExerciseImpl> getExerciseCollection();

    void setExerciseCollection(Collection<ExerciseImpl> exerciseCollection);

    @Override
    String toString();
}
