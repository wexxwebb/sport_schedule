package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class ExerciseDataController {

    private services.ExerciseDataService exerciseDataService;

    public services.ExerciseDataService getExerciseDataService() {
        return exerciseDataService;
    }

    @Autowired
    public void setExerciseDataService(services.ExerciseDataService exerciseDataService) {
        this.exerciseDataService = exerciseDataService;
    }

    @RequestMapping(value = "/inner/searchExerciseData", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String searchExerciseData(@RequestParam(value = "term") String term) {
        return exerciseDataService.searchExerciseData(term);
    }

    @RequestMapping(value = "/inner/exerciseData", method = RequestMethod.GET)
    public ModelAndView getExerciseDataList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/exerciseData");
        List<db.entities.ExerciseData> exerciseDataList = exerciseDataService.getExerciseDatalist();
        if (exerciseDataList != null) {
            modelAndView.addObject("exerciseDataList", exerciseDataList);
            return modelAndView;
        }
        modelAndView.addObject("error", "Ошибка при обращении к базе данных");
        return modelAndView;
    }

    @RequestMapping(value = "/inner/addExerciseData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addExerciseData(@RequestParam(value = "name") String name) {
        return exerciseDataService.addExerciseData(name);
    }

    @RequestMapping(value = "/inner/delExerciseData", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String delExerciseData(@RequestParam(value = "id") int id) {
        return exerciseDataService.delExerciseData(id);
    }

}
