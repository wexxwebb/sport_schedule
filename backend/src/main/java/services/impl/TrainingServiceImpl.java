package services.impl;

import com.google.gson.Gson;
import common.Formatted;
import common.Logged;
import common.SDF;
import db.dao._inter.TrainingDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.TrainingImpl;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.TrainingService;
import services.excep.ServiceIsNotAvailableException;

import java.sql.Date;

@SuppressWarnings("Duplicates")
@Service
public class TrainingServiceImpl implements TrainingService {

    @Logged
    private Logger logger;

    @Formatted
    private Gson gson;

    private TrainingDAO trainingDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public String addTraining(long userId, String date) throws ServiceIsNotAvailableException {
        TrainingImpl training = new TrainingImpl(new UserDataImpl(userId),
                new Date(System.currentTimeMillis()), new Date(SDF.getDate(date).get().getTime()));
        try {
            training = trainingDAO.insert(training);
            return gson.toJson(training);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public boolean delTraining(long id) throws ServiceIsNotAvailableException {
        try {
            trainingDAO.delete(id);
            return true;
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public Training getById(long id) throws ServiceIsNotAvailableException {
        try {
            return trainingDAO.getById(id);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }
}

