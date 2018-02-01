package controller;

import common.Logged;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.LoginService;

@Controller
public class LoginController {

    @Logged
    private Logger logger;

    @Autowired
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String auth(@RequestParam(value = "login") String login,
//                       @RequestParam(value = "password") String password,
//                       HttpServletRequest request,
//                       Model model) {
//        if (loginService.checkAuth(login, password)) {
//            request.getSession().setAttribute("login", login);
//            return "redirect:/dashboard";
//        } else {
//            model.addAttribute("authError", "Некорректный логин или пароль");
//            return "login";
//        }
//    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("public/login");
        if (error != null && error != "" && error.equals("access_denied")) {
            modelAndView.addObject("authError", "Некорректный логин или пароль");
        }
        return modelAndView;
    }

}
