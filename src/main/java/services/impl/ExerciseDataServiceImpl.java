package services.impl;

import com.google.gson.Gson;
import common.Autocomplete;
import common.Result;
import db.dao.exerciseData.ExerciseDataDAO;
import db.pojo.ExerciseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ExerciseDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseDataServiceImpl implements ExerciseDataService {

    @Autowired
    private ExerciseDataDAO exerciseDataDAO;

    public ExerciseDataServiceImpl() {
    }

    public String searchExerciseData(String term) {
        Result<List<ExerciseData>> result = exerciseDataDAO.searchByName(term);
        Gson gson = new Gson();
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
}
