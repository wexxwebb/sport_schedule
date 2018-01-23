package controllers;

import db.pojo.Training;
import services.DashboardService;
import services.impl.DashboardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DashboardController extends HttpServlet {

    private DashboardService dashboardService = new DashboardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Training> trainingList = dashboardService.getTrainingList();
        req.setAttribute("trainingList", trainingList);
        req.getRequestDispatcher("/inner/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Training> trainingList = dashboardService.getTrainingList();
//        req.setAttribute("trainingList", trainingList);
//        req.getRequestDispatcher("/inner/dashboard.jsp").forward(req, resp);
    }
}
