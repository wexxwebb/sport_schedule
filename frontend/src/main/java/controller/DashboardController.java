package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.DashboardService;
import util.InnerUser;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class DashboardController {

    @Logged
    private Logger logger;

    private DashboardService dashboardService;

    public DashboardService getDashboardService() {
        return dashboardService;
    }

    @Autowired
    public void setDashboardService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(value = "/inner/dispatcher", method = GET)
    public String dispatchUser(@AuthenticationPrincipal InnerUser user) {
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:../admin/users";
        }
        return "redirect:dashboard";
    }

    @RequestMapping(value = "/inner/dashboard", method = GET)
    public ModelAndView getDashboard(@AuthenticationPrincipal InnerUser user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/dashboard");
        modelAndView.addObject("firstName", user.getFirstName());
        modelAndView.addObject("userId", user.getId());
        modelAndView.addObject("pastTrainingList",
                dashboardService.getPastTriningList(user.getId()));
        modelAndView.addObject("todayTrainingList",
                dashboardService.getTodayTrainingList(user.getId()));
        modelAndView.addObject("futureTrainingList",
                dashboardService.getFutureTrainingList(user.getId()));

        return modelAndView;
    }
}
