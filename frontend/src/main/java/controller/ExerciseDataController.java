package controller;


import common.Logged;
import db.entities._inter.ExerciseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services._inter.ExerciseDataService;
import services.excep.ServiceIsNotAvailableException;


import java.util.List;

import static common.Messages.SERVICE_ERROR;

@Controller
public class ExerciseDataController {

    @Logged
    private Logger logger;

    private ExerciseDataService exerciseDataService;

    @Autowired
    public ExerciseDataController(ExerciseDataService exerciseDataService) {
        this.exerciseDataService = exerciseDataService;
    }

    @RequestMapping(value = "/inner/searchExerciseData", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String searchExerciseData(@RequestParam(value = "term") String term) {
        try {
            return exerciseDataService.searchExerciseData(term);
        } catch (ServiceIsNotAvailableException e) {
            return "[]";
        }
    }

    @RequestMapping(value = "/inner/exerciseData", method = RequestMethod.GET)
    public ModelAndView getExerciseDataList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/exerciseData");
        List<ExerciseData> exerciseDataList = null;
        try {
            exerciseDataList = exerciseDataService.getExerciseDataList();
            modelAndView.addObject("exerciseDataList", exerciseDataList);
            return modelAndView;
        } catch (ServiceIsNotAvailableException e) {
            modelAndView.addObject("error", SERVICE_ERROR);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/inner/addExerciseData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addExerciseData(@RequestParam(value = "name") String name) {
        try {
            return exerciseDataService.addExerciseData(name);
        } catch (ServiceIsNotAvailableException e) {
            return SERVICE_ERROR;
        }
    }

    @RequestMapping(value = "/inner/delExerciseData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String delExerciseData(@RequestParam(value = "id") long id) {
        try {
            exerciseDataService.delExerciseData(id);
            return "1";
        } catch (ServiceIsNotAvailableException e) {
            return "0";
        }
    }

}
