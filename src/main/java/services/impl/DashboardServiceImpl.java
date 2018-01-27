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
    public List<Training> getTrainingList() {
        Result<List<Training>> result;
        if ((result = trainingDAO.getAll()).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }

    }
}
