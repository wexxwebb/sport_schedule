package controllers;

import common.RegisterDataCheck;
import org.apache.log4j.Logger;
import services.impl.RegisterServiceImpl;
import services.impl.SexListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrerController extends HttpServlet {

    private Logger logger = Logger.getLogger(RegistrerController.class);
    private RegisterServiceImpl registerService = new RegisterServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        RegisterDataCheck regCheck = registerService.register(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("password_approve"),
                request.getParameter("name"),
                request.getParameter("last_name"),
                request.getParameter("sex"),
                request.getParameter("birthday")
        );
        if (regCheck.isValid()) {
            request.getSession().setAttribute("login", request.getParameter("login"));
            response.sendRedirect(request.getContextPath() + "/inner/dashboard");
            logger.info("Registrer accept");
        } else {
            if (!regCheck.isLogin()) {
                request.setAttribute("loginError", "Некорректный логин");
            } else request.setAttribute("login", request.getParameter("login"));

            if (!regCheck.isPassword()) {
                request.setAttribute("passwordError", "Пароли не совпадают или пустой пароль");
            } else {
                request.setAttribute("password", request.getParameter("password"));
                request.setAttribute("passwordApprove", request.getParameter("password_approve"));
            }
            if (!regCheck.isName()) {
                request.setAttribute("nameError", "Имя обязательно");
            } else request.setAttribute("name", request.getParameter("name"));
            if (!regCheck.isLastName()) {
                request.setAttribute("lastNameError", "Фамилия обязательна");
            } else request.setAttribute("lastName", request.getParameter("last_name"));
            if (!regCheck.isSex()) {
                request.setAttribute("sexError", "Выберете пол");
            } else request.setAttribute("sex", request.getParameter("sex"));
            if (!regCheck.isBirthday()) {
                request.setAttribute("dateError", "Некорректтная дата");
            } else request.setAttribute("birthday", request.getParameter("birthday"));

            SexListServiceImpl sexList = new SexListServiceImpl();
            request.setAttribute("sexList", sexList.getSexList());
            request.getRequestDispatcher("/register.jsp").forward(request, response);

            logger.info("Registrer denied");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SexListServiceImpl sexList = new SexListServiceImpl();
        request.setAttribute("sexList", sexList.getSexList());
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
