package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static common.Messages.ACCESS_DENIED;

@Controller
public class LoginController {

    @Logged
    private Logger logger;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("public/login");
        if (error != null && !error.isEmpty() && error.equals("access_denied")) {
            logger.debug(ACCESS_DENIED);
            modelAndView.addObject("authError", "Некорректный логин или пароль");
        }
        return modelAndView;
    }

}
