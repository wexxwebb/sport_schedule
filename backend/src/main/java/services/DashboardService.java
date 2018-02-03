package services;

import db.pojo.Training;

import java.io.Serializable;
import java.util.List;

public interface DashboardService extends Serializable {

    List<Training> getTodayTrainingList(int userId);

    List<Training> getFutureTrainingList(int userId);

    List<Training> getPastTriningList(int userId);
}
