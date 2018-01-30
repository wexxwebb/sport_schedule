package service.impl;

import com.google.gson.Gson;
import common.Autocomplete;
import common.Result;
import db.dao.exerciseData.ExerciseDataDAO;
import db.pojo.ExerciseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExerciseDataService;

import java.util.List;

import static common.InsertType.NEW;

@Service
public class ExerciseDataServiceImpl implements ExerciseDataService {

    private ExerciseDataDAO exerciseDataDAO;

    private Gson gson;

    public ExerciseDataDAO getExerciseDataDAO() {
        return exerciseDataDAO;
    }

    @Autowired
    public void setExerciseDataDAO(ExerciseDataDAO exerciseDataDAO) {
        this.exerciseDataDAO = exerciseDataDAO;
    }

    public Gson getGson() {
        return gson;
    }

    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public ExerciseDataServiceImpl() {
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
        ExerciseData exerciseData = new ExerciseData(name);
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
