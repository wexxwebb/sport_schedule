package services._inter;

import db.entities._inter.Training;
import services.excep.ServiceIsNotAvailableException;

import java.io.Serializable;
import java.util.List;

public interface DashboardService extends Serializable {

    List<Training> getTodayTrainingList(long userId) throws ServiceIsNotAvailableException;

    List<Training> getFutureTrainingList(long userId) throws ServiceIsNotAvailableException;

    List<Training> getPastTrainingList(long userId) throws ServiceIsNotAvailableException;
}
