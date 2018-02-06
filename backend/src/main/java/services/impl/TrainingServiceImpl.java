package services.impl;

import com.google.gson.Gson;
import common.Formatted;
import common.Logged;
import common.SDF;
import db.dao._inter.TrainingDAO;
import db.entities.Impl.TrainingImpl;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.TrainingService;

import java.sql.Date;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Logged
    private Logger logger;

    @Formatted
    private Gson gson;

    private TrainingDAO trainingDAO;

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public String addTraining(long userId, String date) {
        TrainingImpl training = new TrainingImpl(new UserDataImpl(userId),
                new Date(System.currentTimeMillis()), new Date(SDF.getDate(date).get().getTime()));
        training = trainingDAO.insert(training);
        return gson.toJson(training);
    }

    @Override
    public String delTraining(long id) {
        if (trainingDAO.delete(id)) {
            return "1";
        }
        return "0";
    }

    @Override
    public Training getById(long id) {
        return trainingDAO.getById(id);
    }
}

