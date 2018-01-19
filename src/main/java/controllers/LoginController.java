package controllers;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private static UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "hello");
//        if (req.getParameter("error")  != null) {
//            req.setAttribute("errorMsg", req.getParameter("error"));
//        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.checkAuth(login, password)) {
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("/inner/page");
        } else {
            req.getRequestDispatcher("/login.jsp?error=invalid_auth").forward(req, resp);
        }
    }
}
