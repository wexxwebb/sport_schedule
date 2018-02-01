package services.impl;

import common.Logged;
import common.Result;
import db.dao.training.TrainingDAO;
import db.pojo.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DashboardService;

import java.util.ArrayList;
import java.util.List;

import static common.TimePeriod.*;


@Component
public class DashboardServiceImpl implements DashboardService {

    @Logged
    private Logger logger;

    @Autowired
    private TrainingDAO trainingDAO;

    public DashboardServiceImpl() {
    }

    public TrainingDAO getTrainingDAO() {
        return trainingDAO;
    }

    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public List<Training> getTodayTrainingList(int userId) {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(userId, TODAY)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Training> getFutureTrainingList(int userId) {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(userId, FUTURE)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Training> getPastTriningList(int userId) {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(userId, PAST)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }
}
