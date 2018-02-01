package services;

import db.pojo.Training;

import java.util.List;

public interface DashboardService {

    List<Training> getTodayTrainingList(int userId);
    List<Training> getFutureTrainingList(int userId);
    List<Training> getPastTriningList(int userId);
}
