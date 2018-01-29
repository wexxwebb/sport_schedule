package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.DashboardService;

@Controller
public class DashboardController {

    private static Logger logger = Logger.getLogger(DashboardController.class);

    private DashboardService dashboardService;

    public DashboardService getDashboardService() {
        return dashboardService;
    }

    @Autowired
    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(value = "/inner/dashboard", method = RequestMethod.GET)
    public ModelAndView getDashboard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/dashboard");
        modelAndView.addObject("pastTrainingList", dashboardService.getPastTriningList());
        modelAndView.addObject("todayTrainingList", dashboardService.getTodayTrainingList());
        modelAndView.addObject("futureTrainingList", dashboardService.getFutureTrainingList());

        return modelAndView;
    }
}
