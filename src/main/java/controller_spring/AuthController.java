package controller_spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.LoginService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam(value = "login") String login,
                       @RequestParam(value = "password") String password,
                       HttpServletRequest request,
                       Model model) {
        if (loginService.checkAuth(login, password)) {
            request.getSession().setAttribute("login", login);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("authError", "Некорректный логин или пароль");
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        logger.info("index");
        return "login";
    }

}
