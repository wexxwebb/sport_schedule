package controller;

import common.Logged;
import common.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services._inter.RegisterService;
import services._inter.SexListService;

import java.util.Map;

@Controller
public class RegisterController {

    @Logged
    private Logger logger;

    private RegisterService registerService;

    private SexListService sexListService;

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Autowired
    public void setSexListService(SexListService sexListService) {
        this.sexListService = sexListService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("public/register");
        modelAndView.addObject("sexList", sexListService.getSexList());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "login", required = false)               String login,
                                 @RequestParam(value = "password", required = false)            String password,
                                 @RequestParam(value = "password_approve", required = false)    String passwordApprove,
                                 @RequestParam(value = "first_name", required = false)           String firstName,
                                 @RequestParam(value = "last_name", required = false)           String lastName,
                                 @RequestParam(value = "sex", required = false)                 String sex,
                                 @RequestParam(value = "birthday", required = false)            String birthday) {

        Result<Map<String, String>> result =
                registerService.register(login, password, passwordApprove, firstName,
                        lastName, sex, birthday);
        ModelAndView modelAndView = new ModelAndView();
        if (result.isSuccess()) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.setViewName("public/register");
        modelAndView.addAllObjects(result.get());
        modelAndView.addObject("sexList", sexListService.getSexList());
        return modelAndView;
    }
}
