package controller;

import common.Logged;
import common.Result;
import db.pojo.Exercise;
import db.pojo.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.ExerciseServise;
import services.TrainingService;

import java.util.List;

@SuppressWarnings("ALL")
@Controller
public class ExerciseController {

    @Logged
    private Logger logger;

    @Autowired
    private ExerciseServise exerciseServise;

    @Autowired
    private TrainingService trainingService;

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

    @RequestMapping(value = "/inner/trainingConsist", method = RequestMethod.GET)
    public ModelAndView getTrainingConsist(@RequestParam(value = "trainingId") int trainingId) {

        ModelAndView modelAndView = new ModelAndView();

        Result<Training> result = trainingService.getById(trainingId);
        if (result.isSuccess()) {
            modelAndView.addObject("training", result.get());
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

    @RequestMapping(value = "/inner/addExercise", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addExercise(@RequestParam(value = "exercise_id") int exerciseid,
                              @RequestParam(value = "training_id") int trainingId,
                              @RequestParam(value = "approach") int approach,
                              @RequestParam(value = "repetition") int repetition,
                              @RequestParam(value = "weigth") int weigth) {

        Result<String> result =
                exerciseServise.addExercise(exerciseid, trainingId,
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
        Result<String> result = exerciseServise.delExercise(id);
        return result.get();
    }
}