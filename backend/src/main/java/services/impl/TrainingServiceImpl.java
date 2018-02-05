package services.impl;

import com.google.gson.Gson;
import common.Logged;
import db.dao._interfaces.TrainingDAO;
import db.entities.Impl.TrainingImpl;
import db.entities.inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._interfaces.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Logged
    private Logger logger;

    private Gson gson;

    private TrainingDAO trainingDAO;

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public String addTraining(int userId, String date) {
        Training training = new TrainingImpl(userId, date);
        training = trainingDAO.insert(training);
        return gson.toJson(training);
    }

    @Override
    public boolean delTraining(int id) {
        return trainingDAO.delete(id);
    }

    @Override
    public Training getById(int id) {
        return trainingDAO.getByid(id);
    }
}

