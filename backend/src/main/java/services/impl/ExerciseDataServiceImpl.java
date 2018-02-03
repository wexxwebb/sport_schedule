package services.impl;

import com.google.gson.Gson;
import common.Autocomplete;
import common.Logged;
import common.Result;
import db.dao.exerciseData.ExerciseDataDAO;
import db.pojo.ExerciseData;
import db.pojo.ExerciseDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ExerciseDataService;

import java.util.List;

import static common.InsertType.NEW;

@Service
public class ExerciseDataServiceImpl implements ExerciseDataService {

    @Logged
    private Logger logger;

    private ExerciseDataDAO exerciseDataDAO;

    private Gson gson;

    public ExerciseDataServiceImpl() {
    }

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Autowired
    public void setExerciseDataDAO(ExerciseDataDAO exerciseDataDAO) {
        this.exerciseDataDAO = exerciseDataDAO;
    }

    public String searchExerciseData(String term) {
        Result<List<ExerciseData>> result = exerciseDataDAO.searchByName(term);
        if (result.isSuccess()) {
            Autocomplete[] autocompletes = new Autocomplete[result.get().size()];
            for (int i = 0; i < result.get().size(); i++) {
                autocompletes[i] =
                        new Autocomplete(result.get().get(i).getName(),
                                Integer.toString(result.get().get(i).getId()));
            }
            return gson.toJson(autocompletes);
        } else
            return "";
    }

    @Override
    public String addExerciseData(String name) {
        ExerciseData exerciseData = new ExerciseDataImpl(name);
        Result<ExerciseData> result = exerciseDataDAO.insert(exerciseData, NEW);
        if (result.isSuccess()) {
            return gson.toJson(result.get());
        }
        return "0";
    }

    @Override
    public String delExerciseData(int id) {
        Result<String> result = exerciseDataDAO.delete(id);
        if (result.isSuccess()) {
            return result.get();
        }
        return "0";
    }

    @Override
    public List<ExerciseData> getExerciseDatalist() {
        Result<List<ExerciseData>> result = exerciseDataDAO.getAll();
        if (result.isSuccess()) {
            return result.get();
        }
        return null;
    }
}
