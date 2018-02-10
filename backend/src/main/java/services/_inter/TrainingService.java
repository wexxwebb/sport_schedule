package services._inter;

import db.entities._inter.Training;
import services.excep.ServiceIsNotAvailableException;

import java.io.Serializable;

public interface TrainingService extends Serializable {

    String addTraining(long userId, String date) throws ServiceIsNotAvailableException;

    boolean delTraining(long id) throws ServiceIsNotAvailableException;

    Training getById(long id) throws ServiceIsNotAvailableException;
}
