package services._interfaces;

import db.entities.inter.Training;

import java.io.Serializable;
import java.util.List;

public interface DashboardService extends Serializable {

    List<Training> getTodayTrainingList(long userId);
    List<Training> getFutureTrainingList(long userId);
    List<Training> getPastTrainingList(long userId);
}
