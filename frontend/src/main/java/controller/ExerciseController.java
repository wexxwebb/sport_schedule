package controller;

import common.Logged;
import db.entities.Impl.ExerciseImpl;
import db.entities._inter.Exercise;
import db.entities._inter.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services._inter.ExerciseService;
import services._inter.TrainingService;
import services.excep.ServiceIsNotAvailableException;

import java.util.*;
import java.util.stream.Collectors;

import static common.Messages.SERVICE_ERROR;

@Controller
public class ExerciseController {

    @Logged
    private Logger logger;

    private ExerciseService exerciseService;

    private TrainingService trainingService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, TrainingService trainingService) {
        this.exerciseService = exerciseService;
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/inner/trainingConsist", method = RequestMethod.GET)
    public ModelAndView getTrainingConsist(@RequestParam(value = "trainingId") int trainingId) {
        ModelAndView modelAndView = new ModelAndView();
        Training training;
        try {
            training = trainingService.getById(trainingId);
            modelAndView.addObject("training", training);
        } catch (ServiceIsNotAvailableException e) {
            logger.debug(SERVICE_ERROR);
            modelAndView.addObject("trainingError", SERVICE_ERROR);
            return modelAndView;
        }
        modelAndView.setViewName("/inner/trainingConsist");
        modelAndView.addObject("exerciseList", new ArrayList<Exercise>(training.getExerciseCollection().stream().sorted((o1, o2) -> o1.getId() <= o2.getId() ? -1 : 1).collect(Collectors.toList())));
        return modelAndView;
    }

    @RequestMapping(value = "/inner/addExercise",
            method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addExercise(@RequestParam(value = "exercise_id") long exerciseId,
                              @RequestParam(value = "training_id") long trainingId,
                              @RequestParam(value = "approach") int approach,
                              @RequestParam(value = "repetition") int repetition,
                              @RequestParam(value = "weight") int weight) {

        try {
            return exerciseService.addExercise(exerciseId, trainingId, approach, repetition, weight);
        } catch (ServiceIsNotAvailableException e) {
            logger.debug(SERVICE_ERROR);
            return SERVICE_ERROR;
        }
    }

    @RequestMapping(value = "/inner/delExercise", method = RequestMethod.POST)
    @ResponseBody
    public String delExercise(@RequestParam(value = "id") long id) {
        try {
            exerciseService.delExercise(id);
            return "1";
        } catch (ServiceIsNotAvailableException e) {
            logger.debug(SERVICE_ERROR);
            return SERVICE_ERROR;
        }
    }
}
