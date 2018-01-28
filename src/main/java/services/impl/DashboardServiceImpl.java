package services.impl;

import common.Result;
import db.connectionManager.ConnectionManager;
import db.dao.training.TrainingDAO;
import db.dao.training.TrainingDAOImpl;
import db.pojo.Training;
import db.connectionManager.ConnectionManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.DashboardService;

import java.util.ArrayList;
import java.util.List;

import static common.TimePeriod.FUTURE;
import static common.TimePeriod.TODAY;
import static common.TimePeriod.PAST;


@Component
public class DashboardServiceImpl implements DashboardService {

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
    public List<Training> getTodayTrainingList() {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(TODAY)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Training> getFutureTrainingList() {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(FUTURE)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Training> getPastTriningList() {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll(PAST)).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }
}
