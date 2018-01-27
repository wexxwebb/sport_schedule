package controller_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.ExerciseDataService;

@Controller
public class ExerciseDataController {

    @Autowired
    private ExerciseDataService exerciseDataService;

    public ExerciseDataService getExerciseDataService() {
        return exerciseDataService;
    }

    public void setExerciseDataService(ExerciseDataService exerciseDataService) {
        this.exerciseDataService = exerciseDataService;
    }

    @RequestMapping(value = "/searchExerciseData", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String searchExerciseData(@RequestParam(value = "term") String term) {
        return exerciseDataService.searchExerciseData(term);
    }
}
