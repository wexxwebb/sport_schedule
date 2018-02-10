package services.impl;

import com.google.gson.Gson;
import common.Autocomplete;
import common.Logged;
import db.dao._inter.ExerciseDataDAO;
import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.ExerciseDataImpl;
import db.entities._inter.ExerciseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.ExerciseDataService;
import services.excep.ServiceIsNotAvailableException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Service
public class ExerciseDataServiceImpl implements ExerciseDataService {

    @Logged
    private Logger logger;

    private ExerciseDataDAO exerciseDataDAO;

    private Gson gson;

    @Autowired
    public ExerciseDataServiceImpl(ExerciseDataDAO exerciseDataDAO, Gson gson) {
        this.exerciseDataDAO = exerciseDataDAO;
        this.gson = gson;
    }

    public String searchExerciseData(String term) throws ServiceIsNotAvailableException {
        try {
            List<ExerciseDataImpl> exerciseDataList = exerciseDataDAO.searchByName(term);
            Object[] autocomplete = exerciseDataList
                    .stream().map(
                            exerciseData -> new Autocomplete(exerciseData.getName(), exerciseData.getId()))
                    .toArray();
            return gson.toJson(autocomplete);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public String addExerciseData(String name) throws ServiceIsNotAvailableException {
        ExerciseDataImpl exerciseData = new ExerciseDataImpl(name);
        try {
            exerciseData = exerciseDataDAO.insert(exerciseData);
            return gson.toJson(exerciseData);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public void delExerciseData(long id) throws ServiceIsNotAvailableException {
        try {
            exerciseDataDAO.delete(id);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }

    @Override
    public List<ExerciseData> getExerciseDataList() throws ServiceIsNotAvailableException {
        try {
            List<ExerciseDataImpl> exerciseDataList = exerciseDataDAO.getAll();
            return new ArrayList<>(exerciseDataList);
        } catch (DataIsNotAvailableException e) {
            logger.error(e);
            throw new ServiceIsNotAvailableException(e);
        }
    }
}
