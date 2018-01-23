package services.impl;

import common.Result;
import db.DAO.training.TrainingDAO;
import db.DAO.training.TrainingDAOImpl;
import db.POJO.Training;
import db.connectionManager.ConnectionManagerImpl;
import services.DashboardService;

import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    private TrainingDAO trainingDAO = new TrainingDAOImpl(ConnectionManagerImpl.getInstance());

    public TrainingDAO getTrainingDAO() {
        return trainingDAO;
    }

    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public List<Training> getTrainingList() {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll()).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }

    }
}
