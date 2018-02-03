package services;

import db.pojo.Training;

import java.io.Serializable;
import java.util.List;

public interface DashboardService extends Serializable {

    List<Training> getTodayTrainingList(long userId);
    List<Training> getFutureTrainingList(long userId);
    List<Training> getPastTriningList(long userId);
}
