package services.impl;

import common.Logged;
import db.dao._interfaces.TrainingDAO;
import db.entities.inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services._interfaces.DashboardService;

import java.util.List;

import static common.TimePeriod.*;


@Component
public class DashboardServiceImpl implements DashboardService {

    @Logged
    private Logger logger;

    private TrainingDAO trainingDAO;

    public DashboardServiceImpl() {
    }

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public List<Training> getTodayTrainingList(long userId) {
        return trainingDAO.getAll(userId, TODAY);
    }

    @Override
    public List<Training> getFutureTrainingList(long userId) {
        return trainingDAO.getAll(userId, FUTURE);
    }

    @Override
    public List<Training> getPastTrainingList(long userId) {
        return trainingDAO.getAll(userId, PAST);
    }
}
