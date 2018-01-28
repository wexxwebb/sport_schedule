package controller_spring;

import common.Result;
import db.pojo.Exercise;
import db.pojo.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.ExerciseServise;
import services.TrainingService;

import java.io.PipedOutputStream;
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

    @RequestMapping(value = "/addExercise", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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

    @RequestMapping(value = "/delExercise", method = RequestMethod.POST)
    @ResponseBody
    public String delExercise(@RequestParam(value = "id") int id) {
        Result<String> result = exerciseServise.delExercise(id);
        return result.get();
    }
}
