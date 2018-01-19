package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.POJO.ExerciseData;
import services.ExerciseDataList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExerciseAjax extends HttpServlet{

    class ForAutocmplete {
        private String label;
        private int id;

        public ForAutocmplete(String label, int id) {
            this.label = label;
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseDataList list = new ExerciseDataList();
        List<ExerciseData> exerciseDatalist = list.getExerciseData(req.getParameter("term"));
        ForAutocmplete[] ar = new ForAutocmplete[exerciseDatalist.size()];
        for (int i = 0; i < exerciseDatalist.size(); i++) {
            ar[i] = new ForAutocmplete(exerciseDatalist.get(i).getName(), exerciseDatalist.get(i).getId());
        }
        Gson gson = new Gson();
        resp.setCharacterEncoding("UTF-16");
        //resp.getWriter().write(gson.toJson(arString, String[].class));
        resp.getWriter().write(gson.toJson(ar, ForAutocmplete[].class));
    }
}
