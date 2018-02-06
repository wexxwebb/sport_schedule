package services.impl;

import com.google.gson.Gson;
import common.Autocomplete;
import common.Logged;
import common.Result;
import db.dao._inter.ExerciseDataDAO;
import db.entities._inter.ExerciseData;
import db.entities.Impl.ExerciseDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services._inter.ExerciseDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static common.InsertType.NEW;

@Service
public class ExerciseDataServiceImpl implements ExerciseDataService {

    @Logged
    private Logger logger;

    @Autowired
    private ExerciseDataDAO exerciseDataDAO;

    @Autowired
    private Gson gson;

    public ExerciseDataServiceImpl() {
    }

    public String searchExerciseData(String term) {
//        Result<List<ExerciseData>> result = exerciseDataDAO.searchByName(term);
//        if (result.isSuccess()) {
//            Autocomplete[] autocompletes = new Autocomplete[result.get().size()];
//            for (int i = 0; i < result.get().size(); i++) {
//                autocompletes[i] =
//                        new Autocomplete(result.get().get(i).getName(),
//                                Long.toString(result.get().get(i).getId()));
//            }
//            return gson.toJson(autocompletes);
//        } else
            return "";
    }

    @Override
    public String addExerciseData(String name) {
        ExerciseDataImpl exerciseData = new ExerciseDataImpl(name);
        exerciseData = exerciseDataDAO.insert(exerciseData);
        if (exerciseData != null) {
            return gson.toJson(exerciseData);
        }
        return "0";
    }

    @Override
    public String delExerciseData(long id) {
        if (exerciseDataDAO.delete(id)) {
            return "1";
        }
        return "0";
    }

    @Override
    public List<ExerciseData> getExerciseDataList() {
        List<ExerciseDataImpl> exerciseDataList = exerciseDataDAO.getAll();
        if (exerciseDataList != null) {
            return new ArrayList<>(exerciseDataList);
        }
        return null;
    }
}
