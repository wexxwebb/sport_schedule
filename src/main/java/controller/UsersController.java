package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UsersController {

    @Logged
    private Logger logger;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users", method = GET)
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/users");
        modelAndView.addObject("userList", userService.getUserList());
        return modelAndView;
    }
}
