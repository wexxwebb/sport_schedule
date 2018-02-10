package services.impl;

import com.google.gson.Gson;
import common.Logged;
import db.dao._inter.ExerciseDAO;
import db.dao._inter.ExerciseDataDAO;
import db.dao._inter.TrainingDAO;
import db.dao.dto.Impl.ExerciseDTOImpl;
import db.dao.dto._inter.ExerciseDTO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseDataImpl;
import db.entities.Impl.ExerciseImpl;
import db.entities.Impl.TrainingImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.ExerciseService;
import services.excep.ServiceIsNotAvailableException;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Logged
    private Logger logger;

    private ExerciseDAO exerciseDAO;

    private TrainingDAO trainingDAO;

    private ExerciseDataDAO exerciseDataDAO;

    private Gson gson;

    @Autowired
    public ExerciseServiceImpl(ExerciseDAO exerciseDAO, TrainingDAO trainingDAO, ExerciseDataDAO exerciseDataDAO, Gson gson) {
        this.exerciseDAO = exerciseDAO;
        this.trainingDAO = trainingDAO;
        this.exerciseDataDAO = exerciseDataDAO;
        this.gson = gson;
    }

    @Override
    public String addExercise(long exerciseId, long trainingId,
                              int approach, int repetition, int weight) throws ServiceIsNotAvailableException {
        try {
            ExerciseDataImpl exerciseData = exerciseDataDAO.getById(exerciseId);
            TrainingImpl training = trainingDAO.getById(trainingId);
            if (exerciseData != null && training != null) {
                ExerciseImpl exercise = new ExerciseImpl(exerciseData,
                        training, approach, repetition, weight);
                exercise = exerciseDAO.insert(exercise);
                if (exercise != null) {
                    ExerciseDTO exerciseDTO = new ExerciseDTOImpl(exercise);
                    return gson.toJson(exerciseDTO);
                }
            }
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
        return "0";
    }

    @Override
    public void delExercise(long id) throws ServiceIsNotAvailableException {
        try {
            exerciseDAO.delete(id);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

}
