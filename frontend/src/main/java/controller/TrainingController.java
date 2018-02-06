package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services._inter.TrainingService;

@Controller
public class TrainingController {

    @Logged
    private Logger logger;

    private TrainingService trainingService;

    public TrainingService getTrainingService() {
        return trainingService;
    }

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "inner/addTraining", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addTraining(@RequestParam(value = "user_id") int userId,
                              @RequestParam(value = "date") String date) {
        String result = trainingService.addTraining(userId, date);
        if (result != null) {
            return result;
        } else {
            return "";
        }
    }

    @RequestMapping(value = "inner/delTraining", method = RequestMethod.POST)
    @ResponseBody
    public String delTraining(@RequestParam(value = "id") int id) {
        return trainingService.delTraining(id);
    }
}
