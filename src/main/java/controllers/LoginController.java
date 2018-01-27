package controllers;

import services.LoginService;
import services.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (loginService.checkAuth(login, password)) {
            request.getSession().setAttribute("login", login);
            response.sendRedirect(request.getContextPath() + "/inner/dashboard");
        } else {
            request.setAttribute("authError", "Некорректный логин или пароль");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
