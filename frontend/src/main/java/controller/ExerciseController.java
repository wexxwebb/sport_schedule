package controller;

import common.Logged;
import common.Result;
import db.entities.inter.Exercise;
import db.entities.inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services._interfaces.ExerciseService;
import services._interfaces.TrainingService;

import java.util.List;

@Controller
public class ExerciseController {

    @Logged
    private Logger logger;

    private ExerciseService exerciseService;

    private TrainingService trainingService;

    public ExerciseService getExerciseService() {
        return exerciseService;
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public TrainingService getTrainingService() {
        return trainingService;
    }

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/inner/trainingConsist", method = RequestMethod.GET)
    public ModelAndView getTrainingConsist(@RequestParam(value = "trainingId") int trainingId) {

        ModelAndView modelAndView = new ModelAndView();

        Training training = trainingService.getById(trainingId);
        if (training != null) {
            modelAndView.addObject("training", training);
        } else {
            modelAndView.addObject("trainingError", "Ошибка");
        }

        modelAndView.setViewName("/inner/trainingConsist");

        Result<List<Exercise>> exerciseResult = exerciseService.getByTrainindId(trainingId);
        if (exerciseResult.isSuccess()) {
            modelAndView.addObject("exerciseList", exerciseResult.get());
            return modelAndView;
        } else {
            modelAndView.addObject("error", exerciseResult.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/inner/addExercise", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addExercise(@RequestParam(value = "exercise_id") int exerciseid,
                              @RequestParam(value = "training_id") int trainingId,
                              @RequestParam(value = "approach") int approach,
                              @RequestParam(value = "repetition") int repetition,
                              @RequestParam(value = "weigth") int weigth) {

        Result<String> result =
                exerciseService.addExercise(exerciseid, trainingId,
                        approach, repetition, weigth);
        if (result.isSuccess()) {
            return result.get();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/inner/delExercise", method = RequestMethod.POST)
    @ResponseBody
    public String delExercise(@RequestParam(value = "id") int id) {
        Result<String> result = exerciseService.delExercise(id);
        return result.get();
    }
}
