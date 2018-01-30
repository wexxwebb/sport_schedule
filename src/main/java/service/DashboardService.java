package service;

import db.pojo.Training;

import java.util.List;

public interface DashboardService {

    List<Training> getTodayTrainingList();
    List<Training> getFutureTrainingList();
    List<Training> getPastTriningList();
}
