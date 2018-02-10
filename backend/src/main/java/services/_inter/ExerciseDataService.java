package services._inter;

import db.entities._inter.ExerciseData;
import services.excep.ServiceIsNotAvailableException;

import java.io.Serializable;
import java.util.List;

public interface ExerciseDataService extends Serializable {

    String searchExerciseData(String term) throws ServiceIsNotAvailableException;

    String addExerciseData(String name) throws ServiceIsNotAvailableException;

    void delExerciseData(long id) throws ServiceIsNotAvailableException;

    List<ExerciseData> getExerciseDataList() throws ServiceIsNotAvailableException;
}
