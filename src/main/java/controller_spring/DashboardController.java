package controller_spring;

import common.Result;
import db.pojo.Training;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.DashboardService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private static Logger logger = Logger.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    public DashboardService getDashboardService() {
        return dashboardService;
    }

    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/dashboard");
        modelAndView.addObject("trainingList", dashboardService.getTrainingList());
        modelAndView.addObject("hi", "hi from)");

        return modelAndView;
    }
}
