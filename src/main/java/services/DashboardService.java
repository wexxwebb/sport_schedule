package services;

import db.pojo.Training;

import java.util.List;

public interface DashboardService {

    List<Training> getTrainingList();
}
