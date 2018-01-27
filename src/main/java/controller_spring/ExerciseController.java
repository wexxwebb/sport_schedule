package controller_spring;

import common.Result;
import db.pojo.Exercise;
import db.pojo.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ExerciseServise;
import services.TrainingService;

import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseServise exerciseServise;

    @Autowired
    TrainingService trainingService;

    public ExerciseServise getExerciseServise() {
        return exerciseServise;
    }

    public void setExerciseServise(ExerciseServise exerciseServise) {
        this.exerciseServise = exerciseServise;
    }

    public TrainingService getTrainingService() {
        return trainingService;
    }

    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/trainingConsist", method = RequestMethod.GET)
    public ModelAndView getTrainingConsist(@RequestParam(value = "trainingId") int trainingId) {

        ModelAndView modelAndView = new ModelAndView();

        Result<Training> trainingResult = trainingService.getById(trainingId);
        if (trainingResult.isSuccess()) {
            modelAndView.addObject("training", trainingResult.get());
        } else {
            modelAndView.addObject("trainingError", "Ошибка");
        }

        modelAndView.setViewName("/inner/trainingConsist");

        Result<List<Exercise>> exerciseResult = exerciseServise.getByTrainindId(trainingId);
        if (exerciseResult.isSuccess()) {
            modelAndView.addObject("exerciseList", exerciseResult.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error", exerciseResult.getMessage());
            return modelAndView;
        }
    }
}
