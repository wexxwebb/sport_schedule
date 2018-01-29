package controller;

import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.TrainingService;

@Controller
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    public TrainingService getTrainingService() {
        return trainingService;
    }

    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "inner/addTraining", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addTraining(@RequestParam(value = "user_id") int userId,
                              @RequestParam(value = "date") String date) {
        Result<String> result;
        if ((result = trainingService.addTraining(userId, date)).isSuccess()) {
            return result.get();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "inner/delTraining", method = RequestMethod.POST)
    @ResponseBody
    public String delTraining(@RequestParam(value = "id") int id) {

        if (trainingService.delTraining(id).isSuccess()) {
            return "1";
        } else {
            return "0";
        }
    }
}
