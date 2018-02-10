package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services._inter.UserService;
import services.excep.ServiceIsNotAvailableException;

import static common.Messages.SERVICE_ERROR;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UsersController {

    @Logged
    private Logger logger;

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users", method = GET)
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/users");
        try {
            modelAndView.addObject("userList", userService.getUserList());
        } catch (ServiceIsNotAvailableException e) {
            logger.error(SERVICE_ERROR);
            modelAndView.addObject("error", SERVICE_ERROR);
        }
        return modelAndView;
    }
}
