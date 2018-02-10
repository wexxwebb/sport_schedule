package services._inter;

import services.excep.ServiceIsNotAvailableException;

import java.io.Serializable;

public interface ExerciseService extends Serializable {

    String addExercise(long trainingId, long exerciseId, int approach,
                       int repetition, int weight) throws ServiceIsNotAvailableException;

    void delExercise(long id) throws ServiceIsNotAvailableException;

}
