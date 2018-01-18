package servlets;

import services.AuthService;
import services.PersonList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Autentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        resp.getWriter().println(message);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthService authService = new AuthService();
        if (authService.auth(login, password)) {
            PersonList personList = new PersonList();
            req.setAttribute("personList", personList.getPersonList());
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("GO OUT!");
        }
    }
}
