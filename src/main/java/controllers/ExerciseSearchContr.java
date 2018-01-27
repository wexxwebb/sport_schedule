package controllers;

import com.google.gson.Gson;
import common.Autocomplete;
import db.pojo.ExerciseData;
import services.impl.ExerciseDataServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExerciseSearchContr extends HttpServlet{

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ExerciseDataServiceImpl list = new ExerciseDataServiceImpl();
//        List<ExerciseData> exerciseDatalist = list.getExerciseData(req.getParameter("term"));
//        Autocomplete[] ar = new Autocomplete[exerciseDatalist.size()];
//        for (int i = 0; i < exerciseDatalist.size(); i++) {
//            ar[i] = new Autocomplete(exerciseDatalist.get(i).getName(), exerciseDatalist.get(i).getId());
//        }
//        Gson gson = new Gson();
//        resp.setCharacterEncoding("UTF-16");
//        resp.getWriter().write(gson.toJson(ar, Autocomplete[].class));
//    }
}
